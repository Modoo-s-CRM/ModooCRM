package com.modoocrm.modoocrm.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    // CLIENT
    CLIENT_NOT_FOUND(404, "Client not found"),
    INVALID_COUNSEL_TYPE(404,"Invalid Counsel Type"),

    // PARENTS
    PARENTS_NOT_FOUND(404, "Parents not found"),

    // COUNSELOR
    COUNSELOR_NOT_FOUND(404,"Counselor not found"),

    // NOTICE
    NOTICE_NOT_FOUND(404, "Notice not found"),
    INVALID_DATE(404,"Previous dates cannot be processed"),

    //SEARCH_FILTER
    MUST_COUNSEL_TYPE(404,"Counsel Type is Required"),

    // IMAGE
    IMAGE_UPLOAD_FAILED(500, "Image Upload Failed"),
    IMAGE_NOT_FOUND(404, "Counsel Image Not Found"),
    IMAGE_FILE_NOT_DELETE(500, "Image Not Delete"),

    // COUNSEL_SCHEDULE
    COUNSEL_SCHEDULE_NOT_FOUND(404, "Counsel Schedule Not Found"),

    // FAMILY
    FAMILY_NOT_FOUND(404, "Family Not Found"),

    // JOB
    JOB_NOT_FOUND(404, "Job Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
