package com.ecommerce.category.model;

import java.util.Date;

import com.ecommerce.category.model.dto.category.CategoryDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private Long parent;
    private Integer sort;
    private Date regDate;
    private Date modDate;

    public Category(CategoryDto categoryDto) {
        this.id = categoryDto.getId();
        this.name = categoryDto.getName();
        this.parent = categoryDto.getParent();
        this.sort = categoryDto.getSort();
        this.regDate = categoryDto.getRegDate();
        this.modDate = categoryDto.getModDate();
    }

}
