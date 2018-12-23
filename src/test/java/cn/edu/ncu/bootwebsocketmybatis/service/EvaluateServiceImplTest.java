package cn.edu.ncu.bootwebsocketmybatis.service;

import cn.edu.ncu.bootwebsocketmybatis.entity.Evaluate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  19:17
 * @package: cn.edu.ncu.bootwebsocketmybatis.service
 * @project: boot-websocket-mybatis
 */
@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EvaluateServiceImplTest {

    private final Logger logger =
            LoggerFactory.getLogger(this.getClass());

    @Autowired
    EvaluateServiceImpl evaluateService;
    @Test
    public void findAllByUserId() {
        System.out.println(evaluateService.findAllByUserId("100546"));
    }

    @Test
    public void addByUserId() {
        Evaluate evaluate=new Evaluate();
        evaluate.setUserId("164142");
        evaluate.setEvaluateInfoId(2);
        evaluateService.addByUserId(evaluate);
    }
    @Test
    public void find(){
        Evaluate evaluate=new Evaluate();
        evaluate.setUserId("113618");
        evaluate.setEvaluateInfoId(6);
        logger.debug("as",evaluateService.findByUserIdAndEvaId(evaluate));
        System.out.println(evaluateService.findByUserIdAndEvaId(evaluate));
    }
}