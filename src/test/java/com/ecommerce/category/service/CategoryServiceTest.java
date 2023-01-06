package com.ecommerce.category.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = { "spring.config.location = classpath:application.yml" })
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Transactional
    void testJpa() {
        // this.categoryService.test();
        // this.categoryRepository.findByParentAndSortGreaterThanEqualOrderBySort((long)
        // 4, 3);

    }

}
