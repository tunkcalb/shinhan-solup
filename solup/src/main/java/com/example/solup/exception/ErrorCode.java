package com.example.solup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE("400", "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED("405", "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND("400", "C003", " Entity Not Found"),
    USER_NOT_FOUND("400", "C004", "User Not Found"),
    INTERNAL_SERVER_ERROR("500", "C004", "Server Error"),
    INVALID_TYPE_VALUE("400", "C005", " Invalid Type Value"),
    ENUM_MISS_MATCH("501", "C007", "enum miss match"),
    DUPLICATE_DB_VALUE("409", "C008", "duplicated input value"),
    NOT_SAME_DATA_VALUE("400", "C009", "Not Same Data Input Value From DB"),
    FORM_LOGIN_BAD_REQUEST("401", "C012", "Login form Bad Request"),
    FORM_LOGIN_INVALID("401", "C013", "Form login invalid Value");

    private final String status;
    private final String code;
    private final String message;

}
