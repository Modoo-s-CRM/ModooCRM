package com.modoocrm.modoocrm.api.main.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MainRepDto {

    private List<MainCounselScheduleDto> counselSchedules;

    private List<MainNoticeDto> notices;

    private List<MainThisMonthClientDto> thisMonthClients;

    private List<MainNextMonthClient> nextMonthClients;
}