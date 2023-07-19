package com.modoocrm.modoocrm.domain.client.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.repository.ClientRepository;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.counselor.service.CounselorService;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final CounselorService counselorService;

    public ClientService(ClientRepository clientRepository, CounselorService counselorService) {
        this.clientRepository = clientRepository;
        this.counselorService = counselorService;
    }

    public void registerClient(Client client, String counselor) {
        Counselor findCounselor = counselorService.findVerifiedCounselor(counselor);
        findCounselor.addClient(client);
        client.setCounselor(findCounselor);
        clientRepository.save(client);
    }

    public Client updateClient(Client client, Long clientId,String counselor) {
        Counselor findCounselor = counselorService.findVerifiedCounselor(counselor);
        Client findClient = findVerifiedClient(clientId);
        Client updateClient = this.setClientIfPresent(client, findClient);
        updateClient.setCounselor(findCounselor);
        updateClient.setUpdateTime(LocalDateTime.now());
        return clientRepository.save(updateClient);
    }

    //내담자 찾기
    public Client findVerifiedClient(Long client) {
        Client findClient = clientRepository.findById(client).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.CLIENT_NOT_FOUND));
        return findClient;
    }

    //Todo 정보 수정 -> 리팩토링 필요
    private Client setClientIfPresent(Client client, Client findClient) {
        Optional.ofNullable(client.getClientName())
                .ifPresent(name -> findClient.setClientName(name));
        Optional.ofNullable(client.getBirth())
                .ifPresent(birth -> findClient.setBirth(birth));
        Optional.ofNullable(client.getAge())
                .ifPresent(age -> findClient.setAge(age));
        Optional.ofNullable(client.getClientGender())
                .ifPresent(gender -> findClient.setClientGender(gender));
        Optional.ofNullable(client.getAddress())
                .ifPresent(address -> findClient.setAddress(address));
        Optional.ofNullable(client.getPhone())
                .ifPresent(phone -> findClient.setPhone(phone));
        Optional.ofNullable(client.getHobby())
                .ifPresent(hobby -> findClient.setHobby(hobby));
        Optional.ofNullable(client.getHeight())
                .ifPresent(height -> findClient.setHeight(height));
        Optional.ofNullable(client.getWeight())
                .ifPresent(weight -> findClient.setWeight(weight));
        Optional.ofNullable(client.getEducationInfo())
                .ifPresent(educationInfo -> findClient.setEducationInfo(educationInfo));
        Optional.ofNullable(client.getMarry())
                .ifPresent(marry -> findClient.setMarry(marry));
        Optional.ofNullable(client.getJob())
                .ifPresent(job -> findClient.setJob(job));
        Optional.ofNullable(client.getCounselType())
                .ifPresent(counselType -> findClient.setCounselType(counselType));
        Optional.ofNullable(client.getCounselMethod())
                .ifPresent(counselMethod -> findClient.setCounselMethod(counselMethod));
        Optional.ofNullable(client.getInflowPath())
                .ifPresent(inflowPath -> findClient.setInflowPath(inflowPath));
        Optional.ofNullable(client.getSymptom())
                .ifPresent(symptom -> findClient.setSymptom(symptom));
        Optional.ofNullable(client.getCounselHistory())
                .ifPresent(counselHistory -> findClient.setCounselHistory(counselHistory));
        Optional.ofNullable(client.getCounselProgress())
                .ifPresent(counselProgress -> findClient.setCounselProgress(counselProgress));
        Optional.ofNullable(client.getFirstCounsel())
                .ifPresent(firstCounsel -> findClient.setFirstCounsel(firstCounsel));
        Optional.ofNullable(client.getSpecialNote())
                .ifPresent(specialNote -> findClient.setSpecialNote(specialNote));
        return findClient;
    }
}
