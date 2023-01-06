package com.ecommerce.category.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        if (categoryDto.getId() == null) {
            log.info("Category Insert  -> name : {}, parent : {}", categoryDto.getName(), categoryDto.getParent());
            categoryDto.setRegDate(new Date());
            Integer cnt = this.categoryRepository.countByParent(categoryDto.getParent());
            categoryDto.setSort(cnt + 1);
            this.categoryRepository.save(categoryDto);
        } else {
            categoryDto = this.update(categoryDto);
        }

        return categoryDto;
    }

    // UPDATE
    public CategoryDto update(CategoryDto categoryDto) {
        Optional<CategoryDto> categoryOpt = this.categoryRepository.findById(categoryDto.getId());

        if (categoryOpt.isPresent()) {
            CategoryDto tempDto = categoryOpt.get();

            String name = categoryDto.getName();
            Integer sort = categoryDto.getSort();

            if (name != null) {
                tempDto.setName(name);
            }
            if (sort != null) {
                sort = this.updateSort(tempDto, sort);
                tempDto.setSort(sort);
            }

            tempDto.setModDate(new Date());

            return tempDto;
        }
        return categoryDto;
    }

    // 순서 변경
    public int updateSort(CategoryDto categoryDto, Integer sort) {
        Long id = categoryDto.getId();
        int from = categoryDto.getSort();
        sort = sort < 1 ? 1 : sort;
        int to = sort;
        int add = -1;
        if (to < from) {
            to = from;
            from = sort;
            add = 1;
        }

        List<CategoryDto> list = this.categoryRepository.findByParentAndSortBetweenOrderBySort(
                categoryDto.getParent(),
                from, to);
        for (CategoryDto category : list) {
            category.setModDate(new Date());
            if (!category.getId().equals(id)) {
                category.setSort(category.getSort() + add);
            }
        }

        if (list.size() < sort) {
            sort = list.size();
        }

        return sort;
    }
}
