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
     * 更新好友关系 主要是好友的分组           还未完成
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
}
