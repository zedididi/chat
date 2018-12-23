package cn.edu.ncu.bootwebsocketmybatis.controller;

import cn.edu.ncu.bootwebsocketmybatis.entity.Evaluate;
import cn.edu.ncu.bootwebsocketmybatis.entity.EvaluateInfo;
import cn.edu.ncu.bootwebsocketmybatis.service.EvaluateInfoServiceImpl;
import cn.edu.ncu.bootwebsocketmybatis.service.EvaluateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/21  9:14
 * @package: cn.edu.ncu.bootwebsocketmybatis.controller
 * @project: boot-websocket-mybatis
 */
@RestController
@RequestMapping("/eva")
public class EvaluateController {

    @Autowired
    EvaluateServiceImpl evaluateService;

    @Autowired
    EvaluateInfoServiceImpl evaluateInfoService;

    /**
     * 添加好友评价
     * @param id 被评价用户
     * @param eva  评价内容
     */
    @PostMapping("/add")
    public void addEvaluate(String id,String eva){
        EvaluateInfo evaluateInfo=evaluateInfoService.findByContent(eva);
        Evaluate evaluate1;
        if (evaluateInfo!=null){              //evaluateinfo中存在此评价内容
            Evaluate evaluate=new Evaluate();
            evaluate.setUserId(id);
            evaluate.setContent(eva);
            evaluate.setEvaluateInfoId(evaluateInfo.getId());
            evaluate1=evaluateService.findByUserIdAndEvaId(evaluate);
            if (evaluate1==null){            //evaluate中不存在此评价
                evaluateService.addByUserId(evaluate);
            }
        }else {                              //evaluateinfo中不存在此评价内容
            evaluateInfo=new EvaluateInfo();
            evaluateInfo.setContent(eva);
            evaluateInfoService.addEvaluateInfo(evaluateInfo); //evaluateinfo中插入此评价内容
            evaluateInfo=evaluateInfoService.findByContent(eva);
            evaluate1=new Evaluate();
            evaluate1.setUserId(id);
            evaluate1.setEvaluateInfoId(evaluateInfo.getId());
            evaluateService.addByUserId(evaluate1);          //evaluate中插入在此评价
        }
    }

    /**
     * 获取用户的好友评价
     * @param id
     * @return
     */
    @GetMapping("/get")
    public List<Evaluate> getEvaluate(String id){
        return evaluateService.findAllByUserId(id);
    }
}
