package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import cn.edu.ncu.bootwebsocketmybatis.service.FriendServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 返回用户的所有好友
     * @param userId
     * @return
     */
    @PostMapping("/getAll")
    public List<Friend> getAllFriendByUserId(String userId){

        List<Friend> friendsAndUser=friendService.findAllByUserId(userId);
        User user=userService.findById(userId);
        Friend userf=new Friend();
        userf.setFriendName(user.getUserName());
        userf.setFriendId(userId);
        userf.setImage(user.getImage());
        userf.setUserId(userId);
        friendsAndUser.add(userf);
        return friendsAndUser;
    }
}
