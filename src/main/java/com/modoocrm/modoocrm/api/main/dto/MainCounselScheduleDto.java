package com.modoocrm.modoocrm.api.main.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class MainCounselScheduleDto implements Comparable<MainCounselScheduleDto>{

    private LocalDateTime todayCounselDate;

    private String counselorName;

    private String clientName;

    private int firstCounselCount;

    private int cureCounselCount;

    private String counselType;

    @Builder
    public MainCounselScheduleDto(LocalDateTime todayCounselDate, String counselorName, String clientName, int firstCounselCount, int cureCounselCount, String counselType) {
        this.todayCounselDate = todayCounselDate;
        this.counselorName = counselorName;
        this.clientName = clientName;
        this.firstCounselCount = firstCounselCount;
        this.cureCounselCount = cureCounselCount;
        this.counselType = counselType;
    }

    @Override
    public int compareTo(MainCounselScheduleDto other) {
        return this.todayCounselDate.compareTo(other.todayCounselDate);
    }

    public static MainCounselScheduleDto emptySchedule(){
        return new MainCounselScheduleDto(null,"해당 날짜에는 상담 일정이 없습니다.", "No Client", 0,0, "No Counsel Type");
    }
}
