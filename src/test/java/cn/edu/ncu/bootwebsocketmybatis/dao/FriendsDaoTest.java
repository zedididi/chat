package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  20:28
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsDaoTest {
    @Autowired
    private FriendsDao dao;

    @Test
    public void findAllByUserId() {
        List<Friend> allByUserId = dao.findAllByUserId("1");

        System.out.println(allByUserId);
    }
}