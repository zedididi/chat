package cn.edu.ncu.bootwebsocketmybatis.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  14:32
 * @package: cn.edu.ncu.bootwebsocketmybatis.entity
 * @project: boot-websocket-mybatis
 */

public class User implements Serializable {

    private String id;
    private String userName;
    private String password;
    private Timestamp createTime;
    private String image;

    public User() {
    }

    public User(String id, String password) {
        super();
        this.id = id;
        this.userName = null;
        this.password = password;
    }

    public User(String id, String userName, String password) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User(String id, String userName, String password, Timestamp createTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", image='" + image + '\'' +
                '}';
    }
}
