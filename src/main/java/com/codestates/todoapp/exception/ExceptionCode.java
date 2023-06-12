package com.codestates.todoapp.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_COMPLETE(404,"체크한 To do는 수정할 수 없습니다."),
    TODO_NOT_FOUND(404,"해당 To do를 찾을 수 없습니다."),
    MEMBER_EXIST(404, "해당 ID가 존재합니다."),
    MEMBER_NOT_FOUND(404,"해당 유저를 찾을 수 없습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
