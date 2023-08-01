package com.modoocrm.modoocrm.api.counselSchedule.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
public class CounselScheduleRegisterDto {

    @NotBlank
    private String theDayCounselDate;

    @Positive
    private int firstCounselCount;

    @PositiveOrZero
    private int cureCounselCount;

    private String nextCounselDate;

    private String note;

}
