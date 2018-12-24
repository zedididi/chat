package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  20:56
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
public interface FriendService {

    List<Friend> findAllByUserId(String userId);
    Friend findByUserId(Friend friend);
    boolean addFriendByUserId(Friend friend);
    boolean updateFriendByUserId(Friend friend);
    boolean updateGroupByUserId(Friend friend);
    boolean updateStatusByUserId(Friend friend);
    boolean deleteFriendByUserId(Friend friend);
    List<Friend> findAllByUserIdAndStatus(String userId,String status);
}
