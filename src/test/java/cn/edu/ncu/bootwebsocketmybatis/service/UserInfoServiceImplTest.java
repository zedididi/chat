package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.UserDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  21:37
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {


    @Autowired
    UserInfoServiceImpl userInfoService;
    @Test
    public void findAll() {
        userInfoService.findAll();
    }

    @Test
    public void findById() {
        userInfoService.findByUserId("100546");
    }

    @Test
    public void addUserInfo() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId("100546");
        System.out.println(userInfoService.addUserInfo(userInfo));
    }

    @Test
    public void updateUserInfo() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId("100546");
        userInfo.setCountry("China");
        userInfoService.updateUserInfo(userInfo);
    }
}