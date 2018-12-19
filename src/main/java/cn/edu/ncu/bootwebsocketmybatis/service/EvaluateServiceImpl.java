package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.EvaluateDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  21:34
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    /**
     * 查询指定用户id的好友印象
     * @param userId
     * @return
     */
    @Override
    public List<Evaluate> findAllByUserId(String userId) {
        return evaluateDao.findAllByUserId(userId);
    }

    /**
     * 添加指定用户id的好友印象
     * @param evaluate
     * @return
     */
    @Override
    public boolean addByUserId(Evaluate evaluate) {
        if (evaluateDao.addByUserId(evaluate)>0)
            return true;
        return false;
    }
}
