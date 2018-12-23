package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import cn.edu.ncu.bootwebsocketmybatis.entity.UserInfo;
import cn.edu.ncu.bootwebsocketmybatis.service.EvaluateServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.FriendServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.GroupServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/20  11:23
 * @package: cn.edu.ncu.bootwebsocketmybatis.controller
 * @project: boot-websocket-mybatis
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private FriendServiceImpl friendService;

    @Autowired
    EvaluateServiceImpl evaluateService;

    /**
     * 返回用户信息
     * @param userId
     * @param friendId
     * @return
     */
    @PostMapping("/get")
    public UserInfo getUserInfo(String userId,String friendId){

        UserInfo userInfo=userInfoService.findByUserId(friendId);
        if (!userId.equals(friendId))                 //在返回好友信息时，userId，friendId用于查询groupName
            userInfo.setGroupName(friendService.findByUserId(new Friend(userId,friendId)).getGroupName());

        userInfo.setEvaluates(evaluateService.findAllByUserId(friendId));
       return userInfo;
    }
}
