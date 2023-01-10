package com.ecommerce.category.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ecommerce.category.core.TestUtils;
import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.model.vo.category.CategoryVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = { "spring.config.location = classpath:application.yml" })
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    List<CategoryDto> categoryDtos = new ArrayList<>();
    List<CategoryVo> categoryVos = new ArrayList<>();
    Long parent = (long) 0;
    int cnt = 0;

    // Create
    @Test
    void testInsert() {
        this.categoryDtos.addAll(TestUtils.getInsertCategory());
        categoryDtos.forEach(category -> {
            category = this.categoryService.create(category);
            cnt++;
        });
        log.info(categoryDtos.toString());
        assertEquals(this.categoryDtos.size(), cnt);
    }

    // Read
    @Test
    void testRead() {
        this.categoryVos = this.categoryService.read(this.parent);
        log.info(categoryVos.toString());
    }

    // Update
    @Test
    void testUpdate() {
        log.info(categoryDtos.toString());
    }

    // Delete
    @Test
    void testDelete() {
        this.categoryService.delete(this.parent);
        this.categoryVos = this.categoryService.read(this.parent);
        log.info(categoryVos.toString());
    }

}
