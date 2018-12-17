package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.User;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  14:40
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
public interface UserService {
    List<User> findAll();
}
