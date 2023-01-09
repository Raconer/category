package com.ecommerce.category.model.vo.category;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.category.model.Category;
import com.ecommerce.category.model.dto.category.CategoryDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoryVo extends Category {
    List<CategoryVo> chidList;

    public CategoryVo(CategoryDto categoryDto, List<CategoryVo> chidList) {
        this.setId(categoryDto.getId());
        this.setParent(categoryDto.getParent());
        this.setName(categoryDto.getName());
        this.setSort(categoryDto.getSort());
        this.setRegDate(categoryDto.getRegDate());
        this.setModDate(categoryDto.getModDate());
        this.chidList = chidList;
    }
}