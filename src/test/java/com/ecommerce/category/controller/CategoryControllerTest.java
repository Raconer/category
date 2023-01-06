package com.ecommerce.category.controller;

// API Post 실행시
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ecommerce.category.core.TimeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testSave() {

        String testName = "test" + TimeUtils.getTimeCd();
        JSONObject obj = new JSONObject();

        obj.put("name", testName);
        obj.put("parent", 0);

        String jsonStr = obj.toJSONString();
        log.info("Request : {}", jsonStr);

        try {
            MvcResult result = mockMvc.perform(post("/api/category")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonStr) // body 값
            ).andReturn();

            log.info("Response : {}", result.getResponse().getContentAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
