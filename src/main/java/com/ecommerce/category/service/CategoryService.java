package com.ecommerce.category.service;

import org.springframework.stereotype.Service;

import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.model.vo.category.CategoryVo;
import com.ecommerce.category.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    // Create
    public CategoryDto insert(CategoryVo categoryVo) {
        log.info("Category Data Insert");
        CategoryDto categoryDto = new CategoryDto();

        if (this.isDuplicate(categoryVo.getName(), categoryVo.getParent())) {
            categoryDto.setCategoryDto(categoryVo);
            this.categoryRepository.save(categoryDto);
        }

        return categoryDto;
    }

    // READ
    // 중복 체크
    public boolean isDuplicate(String name, long parent) {
        return this.categoryRepository.findTopByNameAndParent(name, parent) == null;
    }

}
