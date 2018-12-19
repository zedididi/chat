package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.dao.EvaluateDao;
import cn.edu.ncu.bootwebsocketmybatis.dao.EvaluateInfoDao;
import cn.edu.ncu.bootwebsocketmybatis.entity.EvaluateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  21:32
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@Service
public class EvaluateInfoServiceImpl implements EvaluateInfoService {
    @Autowired
    private EvaluateInfoDao evaluateInfoDao;


    /**
     * 查询所有的评价
     * @return
     */
    @Override
    public List<EvaluateInfo> findAll() {
        return evaluateInfoDao.findAll();
    }

    /**
     * 根据EvaluateInfo表id来查询评价
     * @param id
     * @return
     */
    @Override
    public EvaluateInfo findById(Integer id) {
        return evaluateInfoDao.findById(id);
    }

    /**
     * 根据EvaluateInfo表content：评价内容 来查询评价
     * @param content
     * @return
     */
    @Override
    public EvaluateInfo findByContent(String content) {
        return evaluateInfoDao.findByContent(content);
    }

    /**
     * 添加评价信息
     * @param evaluateInfo
     * @return
     */
    @Override
    public boolean addEvaluateInfo(EvaluateInfo evaluateInfo) {

        if (evaluateInfoDao.findByContent(evaluateInfo.getContent())==null&&evaluateInfoDao.addEvaluateInfo(evaluateInfo)>0){
            return true;
        }
        return false;
    }


}
