package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.ContentDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * 消息服务类
 */
@Service
public class ContentService {

    @Autowired
    private ContentDao contentDao;

    public List<Content> getContentRecords(String from,String to){
        return contentDao.getContentRecords(from,to);
    }

    public void insertContentRecord(Content content){ contentDao.insertContentRecord(content);}
}
