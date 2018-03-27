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
 * 订单金额返还积分测试
 *
 * @author sdevil507
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegralControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 测试积分返还
     */
    @Test
    public void getIntegral() throws Exception {
        // 测试0<amount<=100返还积分
        mvc.perform(MockMvcRequestBuilders.get("/test/getIntegral")
                .param("amount", "88")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        // 测试100<amount<=500返还积分
        mvc.perform(MockMvcRequestBuilders.get("/test/getIntegral")
                .param("amount", "268")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        // 测试500<amount<=1000返还积分
        mvc.perform(MockMvcRequestBuilders.get("/test/getIntegral")
                .param("amount", "786")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        // 测试1000<amount返还积分
        mvc.perform(MockMvcRequestBuilders.get("/test/getIntegral")
                .param("amount", "1680")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}