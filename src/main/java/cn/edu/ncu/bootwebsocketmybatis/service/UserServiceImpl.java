package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.UserDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  14:41
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByName(String userName) {
        return userDao.findByName(userName);
    }

    /**
     * 按id查询用户
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 添加用户         //id手动生成唯一
     * @param user
     * @return   返回注册成功的id  失败返回null
     */
    @Override
    public String addUser(User user) {

        String id=getId();
        while (userDao.findById(id)!=null){      //如果此id数据库中存在，重新生成
            id=getId();
        }
        user.setId(id);
        if (userDao.addUser(user)>0)
            return id;
        return null;
    }

    /**
     * 修改用户信息 用户名 密码
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        if (userDao.updateUser(user)>0)
            return true;
        return false;
    }

    /**
     * 生成id位数为6,开头为1 le： 1~~~~~
     * @return
     */
    public String getId(){
        String s="1";
        Random random=new Random();
        for (int i=0;i<5;i++)
            s+=random.nextInt(10);
        return s;
    }


    /**
     * 登陆 true登陆成功
     * @param user
     * @return
     */
    public boolean login(User user){

        User user1=findById(user.getId());
        if (user1!=null&&user.getPassword().equals(user1.getPassword()))
            return true;
        return false;
    }

    /**
     * 注册 成功返回用户基本信息 userId，username，password
     * @param user
     * @return
     */
    public User register(User user){

        String id=addUser(user);
        if (id==null)
            return null;
        User user1=findById(id);
        System.out.println(user1);
        return findById(id);
    }
}
