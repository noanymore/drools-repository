package me.sdevil507.drools.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * 测试activation-group关键字
 *
 * @author sdevil507
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestActivationGroupControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 测试activation-group关键字
     */
    @Test
    public void testActivationGroup() throws Exception {
        // 测试0<amount<=100返还积分
        mvc.perform(
                MockMvcRequestBuilders.get("/test/testActivationGroup")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}