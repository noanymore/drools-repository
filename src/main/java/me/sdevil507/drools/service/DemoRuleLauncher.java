package me.sdevil507.drools.service;

import me.sdevil507.drools.model.DemoDTO;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 常规规则示例说明执行器
 *
 * @author sdevil507
 */
@Service
public class DemoRuleLauncher {

    @Autowired
    private KieContainer kieContainer;

    /**
     * 指定规则名运行
     * <p>
     * 注意:
     * ----------------------------------------------------------------
     * 1.如果使用指定规则名运行,不可以存在agenda-group属性
     * 2.{@link org.kie.api.runtime.rule.AgendaFilter}接口提供了多个RuleName开头的默认实现
     */
    public void execWithAssignedRuleName() {
        // 创建KieSession
        KieSession kieSession = kieContainer.newKieSession();
        // 执行规则
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("common"));
        // 执行释放
        kieSession.dispose();
    }

    /**
     * 指定agenda-group分组属性名称运行
     */
    public void execWithAssignedAgendaGroupName() {
        // 创建KieSession
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("demo-group").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    /**
     * 使用延迟属性执行
     */
    public void execWithTimer() {
        // 创建KieSession
        KieSession kieSession = kieContainer.newKieSession();
        System.out.println("start:" + LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        // 创建线程池
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());

        // 启动线程执行规则
        //noinspection Convert2Lambda
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                kieSession.fireUntilHalt(new RuleNameEqualsAgendaFilter("timer"));
            }
        }, 0, TimeUnit.SECONDS);

        // 延迟3s后
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 停止
        kieSession.halt();
        kieSession.dispose();
    }

    /**
     * 测试activation-group互斥属性执行
     */
    public void execWithActivationGroup() {
        // 创建KieSession
        KieSession kieSession = kieContainer.newKieSession();
        DemoDTO dto = new DemoDTO();
        dto.setValue(25);
        kieSession.insert(dto);
        kieSession.getAgenda().getAgendaGroup("xor").setFocus();
        System.out.println("before value:" + dto.getValue());
        kieSession.fireAllRules();
        // 获取规则触发改变后的value值
        System.out.println("after value:" + dto.getValue());
        kieSession.dispose();
    }
}
