package com.remindpay.dto;

public enum HttpErrorCode {
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    UNPROCESSABLE_ENTITY(422),
    CONFLICT(409),
    NOT_FOUND(404);

    private final int code;

    HttpErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}