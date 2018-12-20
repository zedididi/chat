package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import cn.edu.ncu.bootwebsocketmybatis.service.FriendServiceImpl;
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

    @PostMapping("/getAll")
    public List<Friend> getAllFriendByUserId(String userId){
        return friendService.findAllByUserId(userId);
    }
}
