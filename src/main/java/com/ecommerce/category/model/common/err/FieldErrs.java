package com.ecommerce.category.model.common.err;

import java.util.List;

import org.springframework.validation.FieldError;

import com.ecommerce.category.core.code.ValidCode;
import com.ecommerce.category.core.utils.ErrorUtil;

import lombok.Data;

@Data
public class FieldErrs {
    private List<FieldErr> errs;

    public FieldErrs(List<FieldError> errors) {
        this.errs = ErrorUtil.getFieldErr(errors);
    }

    public FieldErrs(String field, ValidCode validCode) {
        this.errs = ErrorUtil.getValidateError(field, validCode);
    }

}
