package com.ecommerce.category.service;

import org.springframework.stereotype.Service;

import com.ecommerce.category.mapper.CategoryMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {

    CategoryMapper categoryMapper;

    public void select() {
        int cnt = categoryMapper.count();
        log.info("Category Cnt : {}", cnt);
    }

}
