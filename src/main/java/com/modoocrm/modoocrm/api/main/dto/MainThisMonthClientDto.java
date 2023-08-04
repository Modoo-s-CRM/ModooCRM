package com.modoocrm.modoocrm.api.main.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainThisMonthClientDto {
    private String clientName;
    private String counselType;

    @Builder
    public MainThisMonthClientDto(String clientName, String counselType) {
        this.clientName = clientName;
        this.counselType = counselType;
    }

    public static MainThisMonthClientDto emptyMainThisMonthClientDto(){
        return new MainThisMonthClientDto("해당 월에 내담자는 아직 없습니다.", null);
    }
}
