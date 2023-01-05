package com.ecommerce.category.model.dto.category;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ecommerce.category.model.vo.category.CategoryVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Category")
@NoArgsConstructor
public class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Long parent;
    @Column(nullable = false)
    private int sort;
    @Column(nullable = false)
    private Date regDate;
    @Column(nullable = true)
    private Date modDate;

    public void setCategoryDto(CategoryVo categoryVo) {
        this.name = categoryVo.getName();
        this.parent = categoryVo.getParent();
        this.regDate = new Date();
    }
}