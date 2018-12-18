package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  21:38
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceImplTest {


    @Autowired
    GroupServiceImpl groupService;
    @Test
    public void findAll() {
        System.out.println(groupService.findAll());
    }

    @Test
    public void findByContent() {
        System.out.println(groupService.findByContent("朋友"));
    }

    @Test
    public void addGroup() {
        Group group=new Group();
        group.setName("闺蜜");
        System.out.println(groupService.addGroup(group));
    }
}