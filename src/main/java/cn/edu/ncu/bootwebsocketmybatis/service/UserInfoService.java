package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.UserInfo;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  19:18
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
public interface UserInfoService {

    List<UserInfo> findAll();
    UserInfo findByUserId(String id);
    boolean addUserInfo(UserInfo userInfo);
    boolean updateUserInfo(UserInfo userInfo);
    boolean updateUserInfoNoImage(UserInfo userInfo);
}
