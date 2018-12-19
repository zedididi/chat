package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import cn.edu.ncu.bootwebsocketmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  15:43
 * @package: cn.edu.ncu.bootwebsocketmybatis.controller
 * @project: boot-websocket-mybatis
 */
@RestController
public class UserController {


    @Autowired
    UserService userService;





}
