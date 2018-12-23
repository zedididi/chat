package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Content;
import cn.edu.ncu.bootwebsocketmybatis.service.ContentService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@ServerEndpoint("/chatRoom/{sendId}")
@Component
@RequestMapping("/chatRoom")
public class ChatController {
    /**
     * 键为userID,值为Session，存储在线用户
     */
    private static Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ContentService contentService;

    @OnOpen
    public void OnOpen(Session session,@PathParam("sendId")String sendId){
        onlineUsers.put(sendId,session);
        logger.info(sendId + "用户连接了服务器,目前在线人数为：" + onlineUsers.size());
    }

    @OnClose
    public void OnClose(Session session,@PathParam("sendId")String sendId){
        onlineUsers.remove(sendId);
        logger.info(sendId + "用户关闭了连接,目前在线人数为：" + onlineUsers.size());
    }

    @OnError
    public void OnError(Session session,Throwable throwable){
        logger.info("发生错误：" + throwable.getMessage());
    }

    @OnMessage
    public void OnMessage(Session session,String msg,@PathParam("sendId")String sendId){
        Content content = JSON.parseObject(msg, Content.class);
        System.out.println("msg:"+msg+"    content:"+content.toString());
        String from = content.getSendId();
        String to = content.getReceiveId();
        String message = content.getContent();
        System.out.println(sendId+to+message);
        Timestamp createTime = new Timestamp(new Date().getTime());
        content.setCreateTime(createTime.toString());
        if (onlineUsers.containsKey(to)) {
            Session session_receive = onlineUsers.get(to);
            session_receive.getAsyncRemote().sendText(Content.jsonStr(sendId,to,message,createTime.toString()));
        }

    }

    @RequestMapping("/{sendId}/{receiveId}")
    public String getContentRecords(@PathVariable("sendId") String sendId, @PathVariable("receiveId") String receiveId){
        List<Content> list = contentService.getContentRecords(sendId,receiveId);
        String jsonStr = JSON.toJSONString(list);
        return jsonStr;
    }

    @PostMapping(value = "/send")
    public String send(@RequestBody String json){
        Content content = JSON.parseObject(json,Content.class);
        content.setCreateTime(new Timestamp(new Date().getTime()).toString());
        contentService.insertContentRecord(content);
        return new Timestamp(new Date().getTime()).toString();
    }


}
