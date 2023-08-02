package com.modoocrm.modoocrm.api.counselSchedule.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CounselScheduleRepDto {
    private LocalDateTime theDayCounselDate;

    private Integer firstCounselCount;

    private Integer cureCounselCount;

    private LocalDateTime nextCounselDate;

    private String note;

    @Builder
    public CounselScheduleRepDto(LocalDateTime theDayCounselDate, Integer firstCounselCount, Integer cureCounselCount, LocalDateTime nextCounselDate, String note) {
        this.theDayCounselDate = theDayCounselDate;
        this.firstCounselCount = firstCounselCount;
        this.cureCounselCount = cureCounselCount;
        this.nextCounselDate = nextCounselDate;
        this.note = note;
    }
}
