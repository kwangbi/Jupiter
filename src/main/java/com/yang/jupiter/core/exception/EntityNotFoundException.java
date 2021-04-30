package com.yang.jupiter.core.exception;

import com.yang.jupiter.core.response.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
