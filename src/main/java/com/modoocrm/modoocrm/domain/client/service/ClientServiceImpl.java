package com.modoocrm.modoocrm.domain.client.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.repository.ClientRepository;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.counselor.service.CounselorService;
import com.modoocrm.modoocrm.domain.job.entity.Job;
import com.modoocrm.modoocrm.domain.job.service.JobService;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CounselorService counselorService;

    private final JobService jobService;

    public ClientServiceImpl(ClientRepository clientRepository, CounselorService counselorService, JobService jobService) {
        this.clientRepository = clientRepository;
        this.counselorService = counselorService;
        this.jobService = jobService;
    }

    @Override
    public void registerClient(Client client, String counselor, String job) {
        Counselor findCounselor = counselorService.findVerifiedCounselor(counselor);
        client.setCounselor(findCounselor);
        Job findJob = jobService.findJob(job);
        client.setJob(findJob);
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client, Long clientId, String counselor, String job) {
        Counselor findCounselor = counselorService.findVerifiedCounselor(counselor);
        Job findJob = jobService.findJob(job);
        Client findClient = findVerifiedClient(clientId);
        Client updateClient = this.setClientIfPresent(client, findClient);
        if (updateClient.getCounselProgress().equals("치료 상담")) {
            updateClient.setIsCure(true);
        }
        if (updateClient.getCounselProgress().equals("초진 상담 종결")
                || updateClient.getCounselProgress().equals("치료 상담 종결")
                || updateClient.getCounselProgress().equals("환불")){
            updateClient.setEndCounsel(LocalDate.now());
        }
        if (updateClient.getCounselProgress().equals("초진 상담")){
            updateClient.setIsCure(false);
        }

        updateClient.setCounselor(findCounselor);
        updateClient.setJob(findJob);
        updateClient.setUpdateTime(LocalDateTime.now());
        clientRepository.save(updateClient);
    }

    @Override
    public List<Client> searchFIlterClient(String counselType, String counselorName, String ageGroup,
                                           String clientGender, String counselProgress, String startDate, String endDate) {
        Client.CounselType transferCounselType = this.transferCounselType(counselType);
        return clientRepository.searchFilterClient(transferCounselType, counselorName, ageGroup, clientGender, counselProgress, startDate, endDate);
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
        return clientRepository.findById(client).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.CLIENT_NOT_FOUND));
    }

    //메인 페이지 초진 개수


    @Override
    public List<Client> getThisMonthFirstCounselClient(LocalDateTime startMonth, LocalDateTime endMonth) {
        return clientRepository.getThisMonthFristCounselClient(startMonth,endMonth);
    }

    //x월에 해당하는 모든 내담자 수 가져오기
    @Override
    public int monthFirstCounselCount(LocalDateTime startDate, LocalDateTime endDate) {
        return clientRepository.countByFirstCounselBetween(startDate, endDate);
    }

    //성인,부부,커플,청소년,태교 상담 카운트 수
    @Override
    public int adultCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.adultCouselTypeCount(startDate, endDate);
    }

    @Override
    public int marriedCoupleCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.marriedCoupleCouselTypeCount(startDate, endDate);
    }

    @Override
    public int coupleCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.coupleCouselTypeCount(startDate, endDate);
    }

    @Override
    public int familyCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.familyCounselTypeCount(startDate, endDate);
    }

    @Override
    public int youthCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.youthCouselTypeCount(startDate, endDate);
    }

    @Override
    public int antenatalCount(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) clientRepository.antenatalCouselTypeCount(startDate, endDate);
    }

    @Override
    public List<Client> clientsInYear(LocalDateTime startDate, LocalDateTime endDate) {
        return clientRepository.clientsInYear(startDate, endDate);
    }

    @Override
    public List<Client> clientsInYearAndCure(LocalDateTime startDate, LocalDateTime endDate) {
        return clientRepository.clientsInYearAndCure(startDate, endDate);
    }

    @Override
    public Client findClientName(String name) {
        return clientRepository.findByClientName(name).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.CLIENT_NOT_FOUND)
        );
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    private Client.CounselType transferCounselType(String counselType) {
        if (counselType == null){
            return null;
        }
        switch (counselType) {
            case "성인 상담":
                return Client.CounselType.ADULT;
            case "부부 상담":
                return Client.CounselType.MARRIED_COUPLE;
            case "커플 상담":
                return Client.CounselType.COUPLE;
            case "가족 상담":
                return Client.CounselType.FAMILY;
            case "아동청소년 상담":
                return Client.CounselType.YOUTH;
            case "태교 상담":
                return Client.CounselType.ANTENATAL;
            case "그룹 상담":
                return Client.CounselType.GROUP;
            default:
                throw new BusinessLogicException(ExceptionCode.MUST_COUNSEL_TYPE);
        }

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
//        Optional.ofNullable(client.getJob())
//                .ifPresent(job -> findClient.setJob(job));
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
