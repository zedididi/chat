package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  10:26
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@Repository
public interface GroupDao {


    List<Group> findAll();
    Group findByContent(String name);
    int addGroup(Group group);

}
