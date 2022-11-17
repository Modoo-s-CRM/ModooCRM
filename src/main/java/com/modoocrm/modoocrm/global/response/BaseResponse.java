package com.modoocrm.modoocrm.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "message", "result"})
public class BaseResponse<T> {
    private final String message;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    //요청 성공
    @Builder
    public BaseResponse(T result){
        this.message = BaseResponseStatus.SUCCESS.getMessage();
        this.code = BaseResponseStatus.SUCCESS.getCode();
        this.result = result;
    }

    //요청 실패
    public BaseResponse(BaseResponseStatus status){
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
