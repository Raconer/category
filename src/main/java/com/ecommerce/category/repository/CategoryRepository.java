package com.ecommerce.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.category.model.dto.category.CategoryDto;


public interface CategoryRepository extends JpaRepository<CategoryDto, Long>, JpaSpecificationExecutor<CategoryDto> {

    Integer countByParentAndIdNot(Long parent, Long id);

    @Query(value = "select id from category where id = :parent limit 1", nativeQuery = true)
    Long findIdById(@Param("parent") Long parent);

    List<CategoryDto> findByParentAndSortGreaterThan(Long parent, Integer sort);

}
