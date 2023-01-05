package com.ecommerce.category.model.vo.category;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CategoryVo {
    @NotEmpty
    private String name;
    private long parent;
}