package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import cn.edu.ncu.bootwebsocketmybatis.entity.Group;
import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import cn.edu.ncu.bootwebsocketmybatis.service.FriendServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.GroupServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/19  23:05
 * @package: cn.edu.ncu.bootwebsocketmybatis.controller
 * @project: boot-websocket-mybatis
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendServiceImpl friendService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    GroupServiceImpl groupService;

    /**
     * 返回用户的所有好友
     * @param userId
     * @return
     */
    @PostMapping("/getAll")
    public List<Friend> getAllFriendByUserId(String userId){

        List<Friend> friendsAndUser=friendService.findAllByUserIdAndStatus(userId,"SR");
        return friendsAndUser;
    }

    @GetMapping("/get")
    public Friend getFriendB(String userId,String friendId){
        Friend friend=new Friend(userId,friendId);
        return friendService.findByUserId(friend);
    }

    /**
     * 获取发送添加请求的好友
     * @param userId
     * @return
     */
    @GetMapping("/getReceiveReq")
    public List<Friend> getReceiveRequest(String userId){
        List<Friend> requestList=friendService.findAllByUserIdAndStatus(userId,"R");
        return  requestList;
    }

    /**
     * 获取接受到好友添加请求，但还未响应的好友
     * @param userId
     * @return
     */
    @GetMapping("/getSendReq")
    public List<Friend> getSendRequest(String userId){
        List<Friend> statusList=friendService.findAllByUserIdAndStatus(userId,"S");
        return statusList;
    }


    /**
     * 添加好友
     * @param userId
     * @param friendId
     * @param groupId
     * @return
     */
    @PostMapping("/add")
    public String addFriend(String userId, String friendId, @RequestParam(value = "groupId",required = false) int groupId){

        System.out.println("userId:"+userId+"friendId:"+friendId);
        if (!userId.equals(friendId)) {
            if (userService.findById(friendId) != null) {        //添加的用户存在
                if (groupId == 0)
                    groupId = 1;
                Friend friend = new Friend(userId, friendId, groupId);
                Friend friend1 = new Friend(friendId, userId, groupId);
                friend.setStatus("S");       //表明好友状态还是请求中，且为请求发送方
                friend1.setStatus("R");      //表明好友状态还是请求中，且为请求接受方

                if (friendService.findByUserId(friend) == null) {    //确定好友关系中不存在
                    if (friendService.addFriendByUserId(friend) && friendService.addFriendByUserId(friend1))
                        return "请求发送成功";
                    return "请求发送失败";
                }

                return "好友已存在或等待同意";
            }
            return "此用户不存在";
        }
        return "不可加自己为好友";
    }

    /**
     * 同意好友添加请求
     * @param userId
     * @param friendId
     * @return
     */
    @PostMapping("/agree")
    public String agreeRequest(String userId,String friendId){

        Friend friend=new Friend(userId,friendId);
        friend.setStatus("SR");
        //因为好友关系是单向的，所有要改数据库两条记录

        if (friendService.updateStatusByUserId(friend))
            return "添加同意成功";
        return "添加同意失败";
    }

    /**
     * 返回所有分组
     * @return
     */
    @GetMapping("/getGroup")
    public List<Group> getAllGroup(){
        return groupService.findAll();
    }


    /**
     * 修改好友分组
     * @param userId
     * @param friendId
     * @param groupId
     * @return
     */
    @PostMapping("/updateGroup")
    public String updateGroup(String userId,String friendId,int groupId){

        Friend friend=new Friend(userId,friendId,groupId);
        if (friendService.updateGroupByUserId(friend))
            return "分组更新成功";
        return "分组更新失败";
    }

    /**
     * 删除好友关系
     * @param userId
     * @param friendId
     * @return
     */
    @PostMapping("/delete")
    public String deleteFriend(String userId,String friendId){

        Friend friend=new Friend(userId,friendId);
        if (friendService.deleteFriendByUserId(friend))
            return "删除成功";
        return "删除失败";
    }

}
