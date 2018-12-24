package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Content;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  10:27
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@Repository
public interface ContentDao {

    /**
     * 查询from 与 to 的聊天记录
     * @param sendId
     * @param receiveId
     * @return
     */
    List<Content> getContentRecords(@Param("sendId")String sendId, @Param("receiveId")String receiveId);

    /**
     * 将传入的消息实体插入到数据库当中
     * @param content
     */
    void insertContentRecord(Content content);
}
