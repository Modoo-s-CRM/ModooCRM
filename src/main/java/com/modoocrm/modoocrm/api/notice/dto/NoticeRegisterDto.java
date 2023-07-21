package com.modoocrm.modoocrm.api.notice.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class NoticeRegisterDto {

    @NotBlank
    private String noticeDate;

    @NotBlank
    private String counselSpecial;
}
