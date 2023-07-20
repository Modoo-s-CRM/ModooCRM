package com.modoocrm.modoocrm.api.client.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClientSearchResponseDto {

    private Long clientId;

    private String clientName;

    private LocalDate birth;

    private String counselorName;

    @Builder
    public ClientSearchResponseDto(Long clientId,String clientName, LocalDate birth, String counselorName){
        this.clientId = clientId;
        this.clientName = clientName;
        this.birth = birth;
        this.counselorName = counselorName;
    }
}
