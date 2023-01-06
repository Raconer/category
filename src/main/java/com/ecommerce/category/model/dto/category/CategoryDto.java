package com.ecommerce.category.model.dto.category;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "Category")
public class CategoryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ColumnDefault(value = "0")
    private Long parent;

    @Column
    @ColumnDefault(value = "0")
    private Integer sort;
    @Column(updatable = false, nullable = false)
    private Date regDate;
    @Column(nullable = true, insertable = false)
    private Date modDate;


}