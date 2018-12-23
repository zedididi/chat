package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FriendServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ContentServiceTest.class);

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
        Friend friend=new Friend("176701","113618",1);
        friend.setStatus("test");
        friendService.addFriendByUserId(friend);
    }

    @Test
    public void updateFriendByUserId() {
        Friend friend=new Friend("176701","113618",1);
        friend.setStatus("TYIGI");
        friendService.updateFriendByUserId(friend);
    }

    @Test
    public void deleteFriendByUserId() {
        Friend friend=new Friend("176701","113618");
        friendService.deleteFriendByUserId(friend);

    }

    @Test
    public void findStatus(){
        System.out.println(friendService.findAllByUserIdAndStatus("113618","SR"));
    }
}

