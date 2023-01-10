package com.ecommerce.category.model.dto.category;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "category")
@Table(name = "category")
public class CategoryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Null
    private Long id;

    @NotBlank
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

    // 기본 Insert Date 설정
    public void setInsertData(Long parent) {
        this.regDate = new Date(); // 등록 일자 추가
        this.parent = parent == null ? 0 : parent;
    }

}