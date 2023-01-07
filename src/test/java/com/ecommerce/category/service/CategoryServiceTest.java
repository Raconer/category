package com.ecommerce.category.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ecommerce.category.model.dto.category.CategoryDto;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location = classpath:application.yml" })
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;


    @Test
    @Transactional
    void testJpa() {


    }

}
