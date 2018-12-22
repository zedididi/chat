package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Content;
import cn.edu.ncu.bootwebsocketmybatis.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContentDaoTest {

    @Autowired
    private ContentService contentService;

    @Test
    public void insertContentRecord() {
        Timestamp timestamp = new Timestamp(new Date().getTime());

        contentService.insertContentRecord(new Content("126098","168648","onlineChat",timestamp.toString()));
    }
}