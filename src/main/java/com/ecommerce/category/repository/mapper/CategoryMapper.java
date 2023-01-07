package com.ecommerce.category.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecommerce.category.model.vo.category.CategoryVo;

@Mapper
public interface CategoryMapper {

    List<CategoryVo> findByCategoryInfo(Long parent);

}
