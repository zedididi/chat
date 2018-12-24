package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import cn.edu.ncu.bootwebsocketmybatis.service.FriendServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.UserService;
import cn.edu.ncu.bootwebsocketmybatis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  15:43
 * @package: cn.edu.ncu.bootwebsocketmybatis.controller
 * @project: boot-websocket-mybatis
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    FriendServiceImpl friendService;

    /**
     * 根据 id 查询用户
     * @param id
     * @return
     */
    @GetMapping("/getId")
    public User getUserById(String id){
        return userService.findById(id);
    }

    /**
     * 根据 username 查询用户
     * @param name
     * @return
     */
    @GetMapping("/getName")
    public List<User> getUserByName(String name){
        return userService.findByName(name);
    }

    /**
     * 根据 userName 和 id 查询用户
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getIdOrName")
    public List<User> getUserByIdOrName(String id,String name){
        return userService.findByIdOrName(id, name);
    }









}
