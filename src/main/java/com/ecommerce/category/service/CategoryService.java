package com.ecommerce.category.service;

import java.util.Date;
import java.util.Optional;
import java.util.Locale.Category;

import org.springframework.stereotype.Service;

import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    // Save
    public CategoryDto save(CategoryDto categoryDto) {
        log.info("Check Data : {}", categoryDto.toString());

        if (categoryDto.getId() == null) {
            categoryDto.setRegDate(new Date());
            this.categoryRepository.save(categoryDto);
        } else {
            this.update(categoryDto);
        }

        return categoryDto;
    }

    public void update(CategoryDto categoryDto) {
        log.info("Update Data Check : {}", categoryDto.toString());
        Optional<CategoryDto> categoryOpt = this.categoryRepository.findById(categoryDto.getId());
        if (categoryOpt.isPresent()) {
            CategoryDto tempDto = categoryOpt.get();
            String name = categoryDto.getName();
            Integer sort = categoryDto.getSort();
            if (name != null) {
                tempDto.setName(name);
            }
            if (sort != null) {
                tempDto.setSort(sort);
            }
            tempDto.setModDate(new Date());
        }
    }
}
