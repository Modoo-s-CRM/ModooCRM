package com.modoocrm.modoocrm.domain.counseldiary.service;

import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;

import java.time.LocalDateTime;
import java.util.List;

public interface CounselScheduleService {

    void registerCounselSchedule(Long clientId, CounselSchedule counselSchedule);

    void updateCounselSchedule(Long counselScheduleId, CounselSchedule counselSchedule);

    CounselSchedule getCounselSchedule(Long clientId, String date);

    List<CounselSchedule> getCounselSchedules(LocalDateTime startTime, LocalDateTime endTime);
}
