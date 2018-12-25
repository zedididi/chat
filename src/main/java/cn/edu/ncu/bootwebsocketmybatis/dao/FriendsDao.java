package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import org.apache.ibatis.annotations.Param;
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
    List<Friend> findAllByUserIdAndStatus(@Param("userId")String userId, @Param("status")String status);
    Friend findByUserId(Friend friend);
    int addFriendByUserId(Friend friend);
    int updateFriendByUserId(Friend friend);
    int updateGroupByUserId(Friend friend);
    int updateStatusByUserId(Friend friend);
    int deleteFriendByUserId(Friend friend);
}
