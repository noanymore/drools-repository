package me.sdevil507.drools.controller;

import me.sdevil507.drools.model.OrderIntegralDTO;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试积分规则Controller
 * <p>
 * 测试规则的执行与关键字agenda-group作用
 *
 * @author sdevil507
 */
@RestController
@RequestMapping("/test")
public class IntegralController {

    /**
     * KieContainer对象
     */
    @Autowired
    private KieContainer kieContainer;

    /**
     * 根据订单金额返还积分接口
     * <p>
     * 调用order_integral.drl中规则，这是最常见的规则判断
     *
     * @param amount 订单金额
     */
    @GetMapping("/getIntegral")
    public void getIntegral(Double amount) {
        // 设置FACT对象
        OrderIntegralDTO orderIntegralDTO = new OrderIntegralDTO();
        orderIntegralDTO.setAmount(amount);
        // 获取session
        KieSession kieSession = kieContainer.newKieSession();
        // 向working memory中插入FACT
        kieSession.insert(orderIntegralDTO);
        // 调用agenda分组规则
        kieSession.getAgenda().getAgendaGroup("integral").setFocus();
        // 执行规则匹配
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则!");
        // 释放
        kieSession.destroy();
        // 显示返回积分
        System.out.println("获得返还积分:" + orderIntegralDTO.getIntegral());
    }
}
