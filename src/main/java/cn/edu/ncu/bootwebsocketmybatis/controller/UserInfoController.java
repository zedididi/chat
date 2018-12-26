package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import cn.edu.ncu.bootwebsocketmybatis.entity.UserInfo;
import cn.edu.ncu.bootwebsocketmybatis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;

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
    private UserServiceImpl userService;

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

    /**
     * 更新用户信息
     * @param userId
     * @param sex
     * @param birthday
     * @param phone
     * @param email
     * @param country
     * @param city
     * @param userName
     * @return
     */
    @PostMapping("/update")
    public String update(String userId, String sex, Long birthday,String phone,String email,String country,String city,String userName){

        Date date=new Date(birthday);
        Timestamp timestamp=new Timestamp(birthday);
        UserInfo userInfo=new UserInfo(userId,"",sex,timestamp,phone,email,country,city,userName);
        User user=new User(userId,userName,"");
        if (userInfoService.updateUserInfoNoImage(userInfo)&&userService.updateUserName(user))
            return "成功";
        return "失败";
    }
}
