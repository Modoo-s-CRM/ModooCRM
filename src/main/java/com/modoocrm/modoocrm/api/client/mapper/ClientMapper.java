package com.modoocrm.modoocrm.api.client.mapper;

import com.modoocrm.modoocrm.api.client.dto.ClientInfoResponseDto;
import com.modoocrm.modoocrm.api.client.dto.ClientRegisterDto;
import com.modoocrm.modoocrm.api.client.dto.ClientSearchFilterRepDto;
import com.modoocrm.modoocrm.api.client.dto.ClientSearchResponseDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.counselimage.entity.CounselImage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .counselType(clientRegisterDto.getCounselType())
                .counselMethod(clientRegisterDto.getCounselMethod())
                .inflowPath(clientRegisterDto.getInflowPath())
                .symptom(clientRegisterDto.getSymptom())
                .symptomGrade(clientRegisterDto.getSymptomGrade())
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

    public ClientInfoResponseDto clientToClientInfoResponseDto(Client findclient){
        Optional<CounselImage> counselImageOptional = Optional.ofNullable(findclient.getCounselImage());
        return ClientInfoResponseDto.builder()
                .clientName(findclient.getClientName())
                .birth(findclient.getBirth())
                .age(findclient.getAge())
                .clientGender(findclient.getClientGender())
                .address(findclient.getAddress())
                .phone(findclient.getPhone())
                .hobby(findclient.getHobby())
                .height(findclient.getHeight())
                .weight(findclient.getWeight())
                .educationInfo(findclient.getEducationInfo())
                .marry(findclient.getMarry())
                .job(findclient.getJob().getJobGroup())
                .counselType(findclient.getCounselType().getCounselTypeDescription())
                .counselMethod(findclient.getCounselMethod())
                .inflowPath(findclient.getInflowPath())
                .symptom(findclient.getSymptom())
                .symptomGrade(findclient.getSymptomGrade().getSymptomGradeDescription())
                .counselHistory(findclient.getCounselHistory())
                .counselProgress(findclient.getCounselProgress())
                .firstCounsel(findclient.getFirstCounsel())
                .specialNote(findclient.getSpecialNote())
                .counselorName(findclient.getCounselor().getCounselorName())
                .counselorGender(findclient.getCounselor().getCounselorGender())
                //todo Null값 허용
                .applicationFormImagePath(counselImageOptional.map(counselImage -> counselImage.getApplicationFormImagePath()).orElse("내담자의 등록된 상담자료가 없습니다."))
                .selfAptitudeImagePath(counselImageOptional.map(counselImage1 -> counselImage1.getSelfAptitudeImagePath()).orElse("내담자의 등록된 상담자료가 없습니다."))
                .landscapeImagePath(counselImageOptional.map(counselImage2 -> counselImage2.getLandscapeImagePath()).orElse("내담자의 등록된 상담자료가 없습니다."))
                .build();
    }

    public ClientSearchFilterRepDto.ClientSearchFilterInfo clientToSearchFilterInfo(Client client){
        ClientSearchFilterRepDto.ClientSearchFilterInfo clientSearchFilterRepDto = ClientSearchFilterRepDto.ClientSearchFilterInfo.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .clientGender(client.getClientGender())
                .age(client.getAge())
                .counselorName(client.getCounselor().getCounselorName())
                .phone(client.getPhone())
                .marry(client.getMarry())
                .job(client.getJob().getJobGroup())
                .counselType(client.getCounselType().getCounselTypeDescription())
                .counselProgress(client.getCounselProgress())
                .createTime(client.getCreateTime())
                .build();
        return clientSearchFilterRepDto;
    }

    public ClientSearchFilterRepDto clientToClientSearchFilterRepDto(List<Client> clients){
        ClientSearchFilterRepDto clientSearchFilterRepDto = new ClientSearchFilterRepDto();

        List<ClientSearchFilterRepDto.ClientSearchFilterInfo> clientSearchFilterInfos = new ArrayList<>(clients.size());
        for (Client client : clients){
            clientSearchFilterInfos.add(this.clientToSearchFilterInfo(client));
        }
        clientSearchFilterRepDto.setData(clientSearchFilterInfos);
        return clientSearchFilterRepDto;
    }

}
