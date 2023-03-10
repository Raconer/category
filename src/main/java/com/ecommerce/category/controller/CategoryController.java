package com.ecommerce.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.category.model.common.err.FieldErrs;
import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.model.vo.category.CategoryVo;
import com.ecommerce.category.service.CategoryService;
import com.ecommerce.category.validate.category.InsertValid;
import com.ecommerce.category.validate.category.SaveValid;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    SaveValid saveValid;
    InsertValid insertValid;

    // Create
    /**
     * request
     * {
     * "name" : "name", //필수
     * "parent" : 1,
     * "sort" : 1
     * }
     * 
     */
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CategoryDto categoryDto, BindingResult result) {
        insertValid.validate(categoryDto, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new FieldErrs(result.getFieldErrors()));
        }

        categoryDto = this.categoryService.create(categoryDto);

        return ResponseEntity.ok(categoryDto);
    }

    /**
     * request
     * ?id=0
     * 
     * @desc Validate -> ParamExceptHandler.java
     */
    // READ
    @GetMapping
    public ResponseEntity<?> read(@RequestParam(defaultValue = "0") Long parent) {
        List<CategoryVo> categoryVos = this.categoryService.read(parent);
        return ResponseEntity.ok(categoryVos);
    }

    // Update
    /**
     * request
     * {
     * "id" : 1, //필수
     * "name" : "name",
     * "parent" : 1,
     * "sort" : 1
     * }
     * 
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto, BindingResult result) {
        saveValid.validate(categoryDto, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new FieldErrs(result.getFieldErrors()));
        }
        categoryDto = this.categoryService.update(categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    // Delete
    /**
     * request
     * ?id=0
     * 
     * @desc Validate -> ParamExceptHandler.java
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        this.categoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
