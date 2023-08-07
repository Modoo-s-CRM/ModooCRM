package com.modoocrm.modoocrm.api.main.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MainRepDto {

    private List<MainCounselScheduleDto> counselSchedules;

    private List<MainNoticeDto> notices;

    private List<MainThisMonthClientDto> thisMonthClients;

    private List<MainNextMonthClientDto> nextMonthClients;

    @Builder

    public MainRepDto(List<MainCounselScheduleDto> counselSchedules, List<MainNoticeDto> notices, List<MainThisMonthClientDto> thisMonthClients, List<MainNextMonthClientDto> nextMonthClients) {
        this.counselSchedules = counselSchedules;
        this.notices = notices;
        this.thisMonthClients = thisMonthClients;
        this.nextMonthClients = nextMonthClients;
    }
}