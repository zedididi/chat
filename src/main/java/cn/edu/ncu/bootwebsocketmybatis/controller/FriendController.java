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

        List<Friend> friendsAndUser=friendService.findAllByUserId(userId);
        /*User user=userService.findById(userId);
        Friend userf=new Friend();
        userf.setFriendName(user.getUserName());
        userf.setFriendId(userId);
        userf.setImage(user.getImage());
        userf.setUserId(userId);
        friendsAndUser.add(userf);*/
        return friendsAndUser;
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

        if (groupId==0)
            groupId=1;
        if (friendService.addFriendByUserId(new Friend(userId,friendId,groupId)))
            return "成功";
        return "失败";
    }

    /**
     * 返回所有分组
     * @return
     */
    @GetMapping("/getGroup")
    public List<Group> getAllGroup(){
        return groupService.findAll();
    }

    @PostMapping("/updateGroup")
    public String updateGroup(String userId,String friendId,int groupId){

        Friend friend=new Friend(userId,friendId,groupId);
        if (friendService.updateFriendByUserId(friend))
            return "成功";
        return "失败";
    }

}
