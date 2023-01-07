package com.ecommerce.category.model.vo.category;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryVo {
    private Long id;
    private String name;
    private Long parent;
    private Integer sort;
    private Date regDate;
    private Date modDate;
    private int childCnt;
}
