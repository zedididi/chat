package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.Evaluate;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  21:31
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
public interface EvaluateService {

    List<Evaluate> findAllByUserId(String userid);
    boolean addByUserId(Evaluate evaluate);
}
