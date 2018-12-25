package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.FriendsDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  20:57
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendsDao friendsDao;

    /**
     * 查询指定用户所有的好友   包含其分组
     * @param userId
     * @return
     */
    @Override
    public List<Friend> findAllByUserId(String userId) {
        return friendsDao.findAllByUserId(userId);
    }

    /**
     * 查询指定用户和莫个用户是否是好友 好友关系单向储存
     * @param friend
     * @return
     */
    @Override
    public Friend findByUserId(Friend friend) {
        return friendsDao.findByUserId(friend);
    }

    /**
     * 添加好友 ，单向添加
     * @param friend
     * @return
     */
    @Override
    public boolean addFriendByUserId(Friend friend) {
        if (friendsDao.findByUserId(friend)==null&&friendsDao.addFriendByUserId(friend)>0)
            return true;
        return false;
    }

    /**
     * 更新好友关系 好友的分组
     * @param friend
     * @return
     */
    @Override
    public boolean updateGroupByUserId(Friend friend) {
        if (friendsDao.updateGroupByUserId(friend)>0)
            return true;
        return false;
    }

    /**
     * 更新好友关系 好友状态
     * @param friend
     * @return
     */
    @Override
    public boolean updateStatusByUserId(Friend friend) {
        if (friendsDao.updateStatusByUserId(friend)>0)
            return true;
        return false;
    }

    /**
     * 更新好友关系 好友的分组和状态
     * @param friend
     * @return
     */
    @Override
    public boolean updateFriendByUserId(Friend friend) {
        if (friendsDao.updateFriendByUserId(friend)>0)
            return true;
        return false;
    }

    /**
     * 删除好友关系
     * @param friend
     * @return
     */
    @Override
    public boolean deleteFriendByUserId(Friend friend) {
        if (friendsDao.deleteFriendByUserId(friend)>0)
            return true;
        return false;
    }

    /**
     * 根据userId和status来查询好友 好友状态 SR代表已是好友 S代表好友请求发送方 R代表好友请求接受方
     * @param userId
     * @param status
     * @return
     */
    @Override
    public List<Friend> findAllByUserIdAndStatus(String userId, String status) {
        return friendsDao.findAllByUserIdAndStatus(userId,status);
    }
}
