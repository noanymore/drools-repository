package me.sdevil507.drools.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试activation-group关键字Controller
 * <p>
 * 该属性将若干个规则划分成一个组，统一命名。<br/>
 * 在执行的时候，具有相同activation-group属性的规则中只要有一个被执行，其它的规则都不再执行。<br/>
 * 可以用类似salience之类属性来实现规则的执行优先级。<br/>
 * 该属性以前也被称为异或（Xor）组，但技术上并不是这样实现的
 *
 * @author sdevil507
 */
@RestController
@RequestMapping("/test")
public class TestActivationGroupController {

    @Autowired
    private KieContainer kieContainer;

    /**
     * 用于测试activation-group属性，异或规则
     */
    @GetMapping("/testActivationGroup")
    public void testActivationGroup() {
        // 获取session
        KieSession kieSession = kieContainer.newKieSession();
        // 调用agenda分组规则,启用activation_group标记的规则
        kieSession.getAgenda().getAgendaGroup("ag").setFocus();
        // 执行规则匹配
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则!");
        // 释放
        kieSession.destroy();
    }
}
