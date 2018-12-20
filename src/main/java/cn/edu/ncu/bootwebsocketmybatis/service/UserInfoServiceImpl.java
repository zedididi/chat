package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.UserInfoDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  19:21
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserInfo findByUserId(String id) {
        return userInfoDao.findByUserId(id);
    }

    /**
     * 添加用户信息  默认image：static/img/dog.png sex：不详
     * @param userInfo
     * @return
     */
    @Override
    public boolean addUserInfo(UserInfo userInfo) {

        if (userInfoDao.findByUserId(userInfo.getUserId())==null&&userInfoDao.addUserInfo(userInfo)>0)   //添加的用户信息不存在 且插入成功
            return true;
        return false;
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    @Override
    public boolean updateUserInfo(UserInfo userInfo) {

        if (userInfoDao.updateUserInfo(userInfo)>0)
            return true;
        return false;
    }

}
