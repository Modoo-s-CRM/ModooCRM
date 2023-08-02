package com.modoocrm.modoocrm.domain.counseldiary.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.domain.counseldiary.repository.CounselScheduleRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

@Transactional
@Service
public class CounselScheduleServiceImpl implements CounselScheduleService{

    private final CounselScheduleRepository counselScheduleRepository;
    private final ClientService clientService;

    public CounselScheduleServiceImpl(CounselScheduleRepository counselScheduleRepository, ClientService clientService) {
        this.counselScheduleRepository = counselScheduleRepository;
        this.clientService = clientService;
    }

    @Override
    public void registerCounselSchedule(Long clientId, CounselSchedule counselSchedule) {
        Client findClient = clientService.findVerifiedClient(clientId);
        counselSchedule.setClient(findClient);
        counselScheduleRepository.save(counselSchedule);
    }

    @Override
    public void updateCounselSchedule(Long counselScheduleId, CounselSchedule counselSchedule) {
        CounselSchedule findCounselSchedule = this.findVerifiedCounselSchedule(counselScheduleId);
        findCounselSchedule.update(counselSchedule);
        counselScheduleRepository.save(findCounselSchedule);
    }

    @Override
    public CounselSchedule getCounselSchedule(Long clientId, String date) {
        //date 예외처리
        this.checkDate(date);
        Client findClient = clientService.findVerifiedClient(clientId);
        LocalDateTime startTime = this.convertToStartDate(date);
        LocalDateTime endTime = this.convertToEndDate(date);

        return counselScheduleRepository.findCounselSheduleForClient(findClient,startTime,endTime);

    }

    private CounselSchedule findVerifiedCounselSchedule(Long counselScheduleId){
        return counselScheduleRepository.findById(counselScheduleId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COUNSEL_SCHEDULE_NOT_FOUND)
        );
    }

    private void checkDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            throw new BusinessLogicException(ExceptionCode.INVALID_REQUEST_DATE);
        }
    }

    private LocalDateTime convertToStartDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 00:00:00", formatter);
    }

    private LocalDateTime convertToEndDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 23:59:59", formatter);
    }

}
