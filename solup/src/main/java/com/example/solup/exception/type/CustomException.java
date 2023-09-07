package com.example.solup.exception.type;

import com.example.solup.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
