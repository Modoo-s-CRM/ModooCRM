package com.modoocrm.modoocrm.domain.counseldiary.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.domain.counseldiary.repository.CounselScheduleRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    private CounselSchedule findVerifiedCounselSchedule(Long counselScheduleId){
        return counselScheduleRepository.findById(counselScheduleId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COUNSEL_SCHEDULE_NOT_FOUND)
        );
    }
}
