package com.ecommerce.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategoryController {

    @GetMapping
    public ResponseEntity<?> name() {
        log.info("Start Main Rest Controller");
        
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
}
