package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.User;
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
 * @date: Create in 2018/12/17  14:43
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private final Logger logger =
            LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserServiceImpl userService;
    @Test
    public void findAll() {
        System.out.println(userService.findAll());
    }

    @Test
    public void addUser(){
        User user=new User();
        user.setUserName("秋水");
        user.setPassword("123");
         System.out.println(userService.addUser(user));
    }
    @Test
    public void findById(){
        logger.debug(userService.findById("164142").toString());
    }
}