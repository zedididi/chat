package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Content;
import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import cn.edu.ncu.bootwebsocketmybatis.entity.UserInfo;
import cn.edu.ncu.bootwebsocketmybatis.service.ContentService;
import cn.edu.ncu.bootwebsocketmybatis.service.UserInfoServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@ServerEndpoint("/chatRoom/{sendId}")
@Component
@RequestMapping("/chatRoom")
public class ChatController {

    /**
     * 使用一个同步Map存储在线的用户
     * 键为userID,值为Session，存储在线用户
     */
    private static Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(ChatController.class);
    /**
     * 存储某个用户的所有好友
     * 键为某个用户的ID,值为其所有好友的Id集合
     */
    private static Map<String,Set<String>> friends = new ConcurrentHashMap<>();

    /**
     * 存储用户发送图片的路径
     */
    @Value("D:/onlineChat/upload/")
    private String uploadPath;

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserInfoServiceImpl userInfoService;

    /**
     * 好友上线通知其所有的好友,更新状态
     * @param session
     * @param sendId
     */
    @OnOpen
    public void OnOpen(Session session,@PathParam("sendId")String sendId){
        logger.info("这是新上线的用户的Id:"+sendId);
        onlineUsers.put(sendId,session);
        for (Map.Entry<String,Set<String>> entry : friends.entrySet())
            if (entry.getValue().contains(sendId) && onlineUsers.containsKey(entry.getKey()))
                onlineUsers.get(entry.getKey()).getAsyncRemote().sendText(sendId +" loginIn");
    }

    /**
     * 用户下线通知其所有好友其下线了,并作状态的转换
     * @param session
     * @param sendId
     */
    @OnClose
    public void OnClose(Session session,@PathParam("sendId")String sendId){
        onlineUsers.remove(sendId);
        for (Map.Entry<String,Set<String>> entry: friends.entrySet())
            if (entry.getValue().contains(sendId) && onlineUsers.containsKey(entry.getKey()))
                onlineUsers.get(entry.getKey()).getAsyncRemote().sendText(sendId+" loginOut");
    }

    @OnError
    public void OnError(Session session,Throwable throwable){
        logger.info("发生错误：" + throwable.getMessage());
    }

    /**
     * 当客户端使用send()方法发送消息给服务器时,触发该事件
     * 同时服务器进行消息的转发工作,将消息发送给对应的接收者
     * @param session
     * @param msg
     * @param sendId
     */
    @OnMessage
    public void OnMessage(Session session,String msg,@PathParam("sendId")String sendId){
        Content content = JSON.parseObject(msg, Content.class);
        String from = content.getSendId();
        String to = content.getReceiveId();
        String message = content.getContent();
        Timestamp createTime = new Timestamp(new Date().getTime());
        content.setCreateTime(createTime.toString());
        if (onlineUsers.containsKey(to)) {
            logger.info("接收到客户端发来的信息："+content.getContent());
            Session session_receive = onlineUsers.get(to);
            session_receive.getAsyncRemote().sendText(Content.jsonStr(sendId,to,message,createTime.toString()));
        }

    }

    /**
     * 以Json数据的形式返回 sendId 与 receiveId两个用户的聊天记录
     * @param sendId
     * @param receiveId
     * @return
     */
    @RequestMapping("/{sendId}/{receiveId}")
    public String getContentRecords(@PathVariable("sendId") String sendId, @PathVariable("receiveId") String receiveId){
        List<Content> list = contentService.getContentRecords(sendId,receiveId);
        String jsonStr = JSON.toJSONString(list);
        return jsonStr;
    }

    /**
     * 将消息存储至数据库当中
     * @param json
     * @return
     */
    @PostMapping(value = "/send")
    public String send(@RequestBody String json){
        Content content = JSON.parseObject(json,Content.class);
        content.setCreateTime(new Timestamp(new Date().getTime()).toString());
        contentService.insertContentRecord(content);
        return new Timestamp(new Date().getTime()).toString();
    }

    /**
     * 返回好友的在线状态 在线还是离线
     * friend_id中包含了某个用户的所有好友id
     * 好友的id用" "进行分隔,字符串的最后还包含了请求查询的Id
     * @param friend_id
     * @return
     */
    @PostMapping(value = "/status")
    public String getStatus(String friend_id){
        String json = "";
        String [] friend = friend_id.split(" ");
        if (!friends.containsKey(friend[friend.length-1])){
            Set<String> set = new HashSet<>();
            for (int i = 0;i<friend.length-1;i++)
                set.add(friend[i]);
            friends.put(friend[friend.length-1],set);
        }
        for (int i = 0;i<friend.length-1;i++)
            json += onlineUsers.containsKey(friend[i]) +" ";
        return json;
    }

    /**
     * 将用户发送的图片进行下载到本地
     * @param file
     * @param sendId
     * @param receiveId
     * @return
     * @throws IOException
     */
    @PostMapping("/upload/picture")
    public String getUploadPciturePath(@RequestParam("file") MultipartFile file,String sendId,String receiveId)throws IOException {
        String fileOriginName = file.getOriginalFilename();
        logger.info(fileOriginName);
        String fileType = "."+fileOriginName.split("[\\.]")[1];
        String fileId = generateFileId();
        if (file!=null){
            File outFile = new File(uploadPath+"picture/"+sendId+"/"+fileId+fileType);
            FileUtils.copyInputStreamToFile(file.getInputStream(),outFile);
        }
        return "/picture/"+sendId+"/"+fileId+fileType;
    }

    /**
     * 将用户发送的语音消息发送到本地
     * @param audio
     * @param sendId
     * @return
     * @throws IOException
     */
    @PostMapping("/upload/audio")
    public String getUploadAudioPath(@RequestParam("audio")MultipartFile audio,String sendId)throws IOException{
        logger.info(sendId+"发来了音频");
        String audioId = generateFileId()+".mp3";
        int seconds = 0;
        if (audio!=null){
            File out = new File(uploadPath +"audio/" +sendId+"/"+audioId);
            FileUtils.copyInputStreamToFile(audio.getInputStream(),out);
        }
        return "/audio/"+sendId +"/"+audioId ;
    }

    /**
     * 生成一个8位数字的文件名
     * @return
     */
    private static String generateFileId(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0;i<=7;i++)
            builder.append(random.nextInt(10));
        return builder.toString();
    }

    /**
     *获取上线用户数据
     * @return
     */
    @GetMapping("/getOnline")
    public List<UserInfo> getOnline(){

        List<UserInfo> users=new ArrayList<>();
        for (Map.Entry<String,Session> entry : onlineUsers.entrySet() ){
            users.add(userInfoService.findByUserId(entry.getKey()));
        }

        System.out.println(users);
        return users;
    }


    /**
     * 获取所有上线用户数据
     * @return
     */
    @GetMapping("/getAll")
    public List<UserInfo> getAll(){
        return userInfoService.findAll();
    }

    @GetMapping("/getOffline")
    public List<UserInfo> getOffline(){

        List<UserInfo> onlineUserList=new ArrayList<>();
        for (Map.Entry<String,Session> entry : onlineUsers.entrySet() ){
            onlineUserList.add(userInfoService.findByUserId(entry.getKey()));
        }

        List<UserInfo> allUserList =userInfoService.findAll();

        System.out.print("AllUser:"+allUserList.size());
        allUserList.removeAll(onlineUserList);
        System.out.println("onlineUser:"+onlineUserList+"\nofflineUser:"+allUserList);
        return allUserList;

    }

}