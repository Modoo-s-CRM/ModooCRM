package com.modoocrm.modoocrm.global.error.exception;

import com.modoocrm.modoocrm.global.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    BaseResponseStatus status;
}
