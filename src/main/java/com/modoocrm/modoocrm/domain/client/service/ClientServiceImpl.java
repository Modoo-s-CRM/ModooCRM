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
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final CounselorService counselorService;

    public ClientServiceImpl(ClientRepository clientRepository, CounselorService counselorService) {
        this.clientRepository = clientRepository;
        this.counselorService = counselorService;
    }
    @Override
    public void registerClient(Client client, String counselor) {
        Counselor findCounselor = counselorService.findVerifiedCounselor(counselor);
        findCounselor.addClient(client);
        client.setCounselor(findCounselor);
        clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client, Long clientId,String counselor) {
        Counselor findCounselor = counselorService.findVerifiedCounselor(counselor);
        Client findClient = findVerifiedClient(clientId);
        Client updateClient = this.setClientIfPresent(client, findClient);
        updateClient.setCounselor(findCounselor);
        updateClient.setUpdateTime(LocalDateTime.now());
        return clientRepository.save(updateClient);
    }

    @Override
    public Client getClientInfo(Long clientId) {
        return this.findVerifiedClient(clientId);
    }

    @Override
    public List<Client> searchClient(String keyword) {
        return clientRepository.findByClientNameContaining(keyword);
    }

    //내담자 찾기
    public Client findVerifiedClient(Long client) {
        Client findClient = clientRepository.findById(client).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.CLIENT_NOT_FOUND));
        return findClient;
    }

    //x월에 해당하는 모든 내담자 수 가져오기
    @Override
    public int monthFirstCounselCount(LocalDateTime startDate, LocalDateTime endDate){
        return clientRepository.countByFirstCounselBetween(startDate,endDate);
    }

    //성인,부부,커플,청소년,태교 상담 카운트 수
    @Override
    public int adultCount(LocalDateTime startDate, LocalDateTime endDate){
        return  (int) clientRepository.adultCouselTypeCount(startDate,endDate);
    }

    @Override
    public int marriedCoupleCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.marriedCoupleCouselTypeCount(startDate,endDate);
    }

    @Override
    public int coupleCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.coupleCouselTypeCount(startDate,endDate);
    }

    @Override
    public int familyCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.familyCounselTypeCount(startDate,endDate);
    }

    @Override
    public int youthCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.youthCouselTypeCount(startDate,endDate);
    }

    @Override
    public int antenatalCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.antenatalCouselTypeCount(startDate,endDate);
    }

    //Todo 정보 수정 -> 리팩토링 필요
    public Client setClientIfPresent(Client client, Client findClient) {
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
