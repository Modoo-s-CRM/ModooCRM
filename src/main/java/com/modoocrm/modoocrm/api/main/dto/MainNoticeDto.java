package com.modoocrm.modoocrm.api.main.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MainNoticeDto implements Comparable<MainNoticeDto>{

    private LocalDateTime noticeDate;

    private String counselSpecial;

    @Builder

    public MainNoticeDto(LocalDateTime noticeDate, String counselSpecial) {
        this.noticeDate = noticeDate;
        this.counselSpecial = counselSpecial;
    }

    @Override
    public int compareTo(MainNoticeDto other) {
        return this.noticeDate.compareTo(other.noticeDate);
    }

    public static MainNoticeDto emptyNoticeDto(){
        return new MainNoticeDto(null, "해당 날짜에 특이사항이 없습니다.");
    }
}
