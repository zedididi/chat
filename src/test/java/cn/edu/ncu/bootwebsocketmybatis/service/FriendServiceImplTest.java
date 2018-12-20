package cn.edu.ncu.bootwebsocketmybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/19  22:41
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendServiceImplTest {


    @Autowired
    FriendServiceImpl friendService;
    @Test
    public void findAllByUserId() {
        System.out.println(friendService.findAllByUserId("113618"));
    }

    @Test
    public void findByUserId() {
    }

    @Test
    public void addFriendByUserId() {
    }

    @Test
    public void updateFriendByUserId() {
    }

    @Test
    public void deleteFriendByUserId() {
    }
}