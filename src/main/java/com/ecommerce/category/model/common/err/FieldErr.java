package com.ecommerce.category.model.common.err;

import com.ecommerce.category.core.code.ValidCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErr {
    private String field;
    private String msg;

    public FieldErr(String field, ValidCode validCode) {
        this.field = field;
        this.msg = validCode.getMsg();
    }
}
