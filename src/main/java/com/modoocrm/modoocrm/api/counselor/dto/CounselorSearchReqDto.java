package com.modoocrm.modoocrm.api.counselor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CounselorSearchReqDto {

    private Integer date;

    private String counselorName;

    private String counselType;

    public CounselorSearchReqDto(Integer date, String counselorName, String counselType){
        this.date = date;
        this.counselorName = counselorName;
        this.counselType = counselType;
    }

}
