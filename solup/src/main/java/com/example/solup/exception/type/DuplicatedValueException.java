package com.example.solup.exception.type;

import com.example.solup.exception.ErrorCode;

public class DuplicatedValueException extends CustomException {

    public DuplicatedValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public DuplicatedValueException(String message) {
        super(message, ErrorCode.DUPLICATE_DB_VALUE);
    }
}
