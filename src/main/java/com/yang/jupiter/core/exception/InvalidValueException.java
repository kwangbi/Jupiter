package com.yang.jupiter.core.exception;

import com.yang.jupiter.core.response.ErrorCode;

public class InvalidValueException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
