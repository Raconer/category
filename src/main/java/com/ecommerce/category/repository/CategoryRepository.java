package com.ecommerce.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecommerce.category.model.dto.category.CategoryDto;


public interface CategoryRepository extends JpaRepository<CategoryDto, Long>, JpaSpecificationExecutor<CategoryDto> {

    Integer countByParent(Long parent);

    List<CategoryDto> findByParentOrderBySort(Long parent);

    List<CategoryDto> findByParentAndSortGreaterThan(Long parent, Integer sort);

}
