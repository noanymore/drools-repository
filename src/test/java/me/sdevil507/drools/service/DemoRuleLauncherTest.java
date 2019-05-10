package me.sdevil507.drools.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 规则相关测试
 *
 * @author sdevil507
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRuleLauncherTest {

    @Autowired
    private DemoRuleLauncher launcher;

    /**
     * 测试指定规则名称运行
     */
    @Test
    public void execWithAssignedRuleName() {
        launcher.execWithAssignedRuleName();
    }

    /**
     * 测试指定agenda-group名称运行
     */
    @Test
    public void execWithAssignedAgendaGroupName() {
        launcher.execWithAssignedAgendaGroupName();
    }

    @Test
    public void execWithTimer() {
        launcher.execWithTimer();
    }

    @Test
    public void execWithActivationGroup() {
        launcher.execWithActivationGroup();
    }
}