package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  10:26
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@Repository
public interface FriendsDao {
    List<Friend> findAllByUserId(String userId);
    Friend findByUserId(Friend friend);
    int addFriendByUserId(Friend friend);
    int updateFriendByUserId(Friend friend);
    int deleteFriendByUserId(Friend friend);
}
