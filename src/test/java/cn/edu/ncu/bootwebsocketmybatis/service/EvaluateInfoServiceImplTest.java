package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.EvaluateInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  20:12
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EvaluateInfoServiceImplTest {

    @Autowired
    EvaluateInfoServiceImpl evaluateInfoService;
    @Test
    public void findAll() {
        System.out.println(evaluateInfoService.findAll());
    }

    @Test
    public void findById() {
        System.out.println(evaluateInfoService.findById(1));
    }

    @Test
    public void findByContent() {
        System.out.println(evaluateInfoService.findByContent("阳光"));
    }

    @Test
    public void addEvaluateInfo() {
        EvaluateInfo evaluateInfo=new EvaluateInfo();
        evaluateInfo.setContent("好看");
        System.out.println(evaluateInfoService.addEvaluateInfo(evaluateInfo));
    }
}