package com.modoocrm.modoocrm.domain.counseldiary.service;

import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;

public interface CounselScheduleService {

    void registerCounselSchedule(Long clientId, CounselSchedule counselSchedule);

    void updateCounselSchedule(Long counselScheduleId, CounselSchedule counselSchedule);
}
