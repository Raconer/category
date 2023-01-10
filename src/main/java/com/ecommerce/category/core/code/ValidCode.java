package com.ecommerce.category.core.code;

import lombok.Getter;

@Getter
public enum ValidCode {
    REQUIRED("required", "This is required data."),
    ALREADY("already", "This data already exists."),
    CHECK("check_data", "Please check data.");

    private String code;
    private String msg;

    private ValidCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
