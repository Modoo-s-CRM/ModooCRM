package com.modoocrm.modoocrm.api.client.mapper;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ClientMapper {

    public Client clientRegisterDtoToClient(ClientRegisterDto clientRegisterDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime firstCounselDateTime = LocalDateTime.parse(clientRegisterDto.getFirstCounsel(),formatter);

        return Client.builder()
                .clientName(clientRegisterDto.getClientName())
                .birth(LocalDate.parse(clientRegisterDto.getBirth()))
                .age(clientRegisterDto.getAge())
                .clientGender(clientRegisterDto.getClientGender())
                .address(clientRegisterDto.getAddress())
                .phone(clientRegisterDto.getPhone())
                .hobby(clientRegisterDto.getHobby())
                .height(clientRegisterDto.getHeight())
                .weight(clientRegisterDto.getWeight())
                .educationInfo(clientRegisterDto.getEducationInfo())
                .marry(clientRegisterDto.getMarry())
                .job(clientRegisterDto.getJob())
                .counselType(clientRegisterDto.getCounselType())
                .counselMethod(clientRegisterDto.getCounselMethod())
                .inflowPath(clientRegisterDto.getInflowPath())
                .symptom(clientRegisterDto.getSymptom())
                .counselHistory(clientRegisterDto.getCounselHistory())
                .counselProgress(clientRegisterDto.getCounselProgress())
                .firstCounsel(firstCounselDateTime)
                .specialNote(clientRegisterDto.getSpecialNote())
                .build();
    }
}
