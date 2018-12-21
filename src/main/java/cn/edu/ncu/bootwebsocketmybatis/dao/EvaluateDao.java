package cn.edu.ncu.bootwebsocketmybatis.dao;

import cn.edu.ncu.bootwebsocketmybatis.entity.Evaluate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  20:03
 * @package: cn.edu.ncu.bootwebsocketmybatis.dao
 * @project: boot-websocket-mybatis
 */
@Repository
public interface EvaluateDao {

    List<Evaluate> findAllByUserId(String userId);
    Evaluate findByUserIdAndEvaId(Evaluate evaluate);
    int addByUserId(Evaluate evaluate);
}
