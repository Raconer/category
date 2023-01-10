package com.ecommerce.category.controller;

// API Post 실행시
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import com.ecommerce.category.core.TimeUtils;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location = classpath:application.yml" })
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc; // 임시 서버

    HashMap<String, Object> data;

    final String path = "/api/category";

    // Create
    @Test
    void testInsert() {
        try {
            data = new HashMap<>();
            data.put("name", "name" + TimeUtils.getTimeCd());
            // 추가 데이터
            // data.put("sort", 10);
            // data.put("parent", 0);
            JSONObject jsonData = new JSONObject(data);
            mockMvc.perform(post(path)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonData.toJSONString()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read
    @Test
    void testGet() {
        try {
            mockMvc.perform(get(path)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .param("parent", "0"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Update
    @Test
    void testUpdate() {
        data = new HashMap<>();
        data.put("id", 30);
        data.put("parent", 29);
        data.put("sort", 2);
        JSONObject jsonData = new JSONObject(data);

        try {
            mockMvc.perform(put(path)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonData.toJSONString()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    @Test
    void testDelete() {
        try {
            mockMvc.perform(delete(path)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .param("id", "0"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
