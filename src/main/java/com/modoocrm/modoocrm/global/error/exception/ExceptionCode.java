package com.modoocrm.modoocrm.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    // MEMBER
    MEMBER_NOT_FOUND(404, "Member not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
