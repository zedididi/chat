package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.Content;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    private static Logger logger = LoggerFactory.getLogger(ContentServiceTest.class);

    @Test
    public void getContentRecords() {
        List<Content> list = contentService.getContentRecords("113618","121411");
        for (Content content:list){
            String sendId = content.getSendId();
            String receiveId =content.getReceiveId();
            String msg = content.getContent();
            String createTime = content.getCreateTime();
            logger.debug(sendId +" " + receiveId +" " +msg +" " + createTime);
        }
    }

    @Test
    public void insertContentRecord() {
        Content content = new Content("126098","168648","a","2018-12-11 09:27:52.481");
        contentService.insertContentRecord(content);
    }
}