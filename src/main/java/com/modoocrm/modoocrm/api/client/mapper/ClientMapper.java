package com.modoocrm.modoocrm.api.client.mapper;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterDto;
import com.modoocrm.modoocrm.api.client.dto.ClientSearchResponseDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public ClientSearchResponseDto clientToClientSearchResponseDto(Client client){
        ClientSearchResponseDto clientSearchResponseDto = ClientSearchResponseDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .birth(client.getBirth())
                .counselorName(client.getCounselor().getCounselorName())
                .build();
        return clientSearchResponseDto;
    }

    public List<ClientSearchResponseDto> clientSearchResponseDtos(List<Client> searchClient){
        List<ClientSearchResponseDto> clientSearchResponseDtos = new ArrayList<>(searchClient.size());
        for (Client client : searchClient){
            clientSearchResponseDtos.add(this.clientToClientSearchResponseDto(client));
        }
        return clientSearchResponseDtos;
    }

}
