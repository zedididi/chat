package cn.edu.ncu.bootwebsocketmybatis.entity;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;

/**
 * 消息实体类
 */
public class Content {
   private String sendId;  //发送者ID
   private String receiveId;    //接收者ID
   private String content;  //消息
   private String createTime;  //发送时间

    public Content(){}

    public Content(String sendId,String receiveId,String content,String createTime){
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.content = content;
        this.createTime = createTime;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 将消息转化给JSON数据发送给客户端
     * @param sendId
     * @param receiveId
     * @param content
     * @param createTime
     * @return
     */
    @Bean
    public static String jsonStr(String sendId,String receiveId,String content,String createTime){
        Content message = new Content(sendId,receiveId,content,createTime);
        return JSON.toJSONString(message);
    }
}
