package com.ecommerce.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.category.model.dto.category.CategoryDto;

public interface CategoryRepository extends JpaRepository<CategoryDto, Long> {
    CategoryDto findTopByNameAndParent(String name, long parent);

}
