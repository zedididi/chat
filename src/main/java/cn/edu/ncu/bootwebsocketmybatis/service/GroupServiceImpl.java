package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.GroupDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  21:31
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@Repository
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public Group findByContent(String name) {
        return groupDao.findByContent(name);
    }

    @Override
    public boolean addGroup(Group group) {

        if (groupDao.findByContent(group.getName())==null&&groupDao.addGroup(group)>0)
            return true;
        return false;
    }
}
