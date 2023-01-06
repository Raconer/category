package com.ecommerce.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.category.model.dto.category.CategoryDto;


public interface CategoryRepository extends JpaRepository<CategoryDto, Long> {

    Integer countByparent(Long parent);

    List<CategoryDto> findByParentAndSortBetweenOrderBySort(Long parent, int fromSort, int toSort);

}

