package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  14:35
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@Repository
public interface UserDao {

     List<User> findAll();
     List<User> findByName(String userName);
     User findById(String id);
     int addUser(User user);
     int updateUser(User user);
}
