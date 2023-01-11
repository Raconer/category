package com.ecommerce.category.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecommerce.category.model.dto.category.CategoryDto;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> findByChildOne(@Param("parent") Long parent, @Param("id") Long id);
    List<CategoryDto> findByChild(Long parent);

    void deleteChild(Long parent);
}
