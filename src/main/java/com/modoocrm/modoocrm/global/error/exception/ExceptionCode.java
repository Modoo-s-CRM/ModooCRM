package com.modoocrm.modoocrm.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    // MEMBER
    CLIENT_NOT_FOUND(404, "Client not found"),

    // PARENTS
    PARENTS_NOT_FOUND(404, "Parents not found"),

    // COUNSELOR
    COUNSELOR_NOT_FOUND(404,"Counselor not found"),

    // IMAGE
    IMAGE_UPLOAD_FAILED(500, "Image Upload Failed"),
    IMAGE_NOT_FOUND(404, "Counsel Image Not Found"),
    IMAGE_FILE_NOT_DELETE(500, "Image Not Delete");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
