package cn.edu.ncu.bootwebsocketmybatis.entity;

import java.math.BigInteger;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  9:07
 * @package: cn.edu.ncu.bootwebsocketmybatis.entity
 * @project: boot-websocket-mybatis
 */
public class Friend {


    private BigInteger id;
    private String userId;    //对应user表的id    用户
    private String friendId;  //对应user表的id    用户好友
    private int groupId;      //对应group表的id   用户好友分组 id
    private String groupName;  //对应group表的name  用户还有分组名


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
