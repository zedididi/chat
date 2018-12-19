package cn.edu.ncu.bootwebsocketmybatis.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  19:52
 * @package: cn.edu.ncu.bootwebsocketmybatis.entity
 * @project: boot-websocket-mybatis
 */

/**
 * 此类是evaluate表和evaluateinfo表的联合查询
 */
public class Evaluate {

    private BigInteger id;
    private String userId;         //对应evaluate表的userid
    private int evaluateInfoId;    //对应evaluate表的evaluateinfoid
    private Timestamp createTime;  //对应evaluate表的createtime
    private String content;        //对应evaluateinfo表的content

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getEvaluateInfoId() {
        return evaluateInfoId;
    }

    public void setEvaluateInfoId(int evaluateInfoId) {
        this.evaluateInfoId = evaluateInfoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", evaluateInfoId=" + evaluateInfoId +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
