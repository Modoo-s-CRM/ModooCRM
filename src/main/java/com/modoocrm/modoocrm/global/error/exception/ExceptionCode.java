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
    COUNSEL_SCHEDULE_NOT_EXIST(404, "보내주신 날짜의 상담 일정은 없습니다."),
    INVALID_REQUEST_DATE(404, "날짜 형식을 올바르게 써주세요"),

    // FAMILY
    FAMILY_NOT_FOUND(404, "Family Not Found"),
    FAMILY_NOT_REGISTER(404, "Family Info Not Register"),

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
