package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.EvaluateInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  20:22
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@Repository
public interface EvaluateInfoDao {

    List<EvaluateInfo> findAll();
    EvaluateInfo findById(Integer id);
    EvaluateInfo findByContent(String content);
    int addEvaluateInfo(EvaluateInfo evaluateInfo);
}
