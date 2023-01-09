package com.ecommerce.category.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location = classpath:application.yml" })
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    @Transactional
    void testJpa() {
        Long parent = (long) 4;
        Integer sort = 3;

    }

}
