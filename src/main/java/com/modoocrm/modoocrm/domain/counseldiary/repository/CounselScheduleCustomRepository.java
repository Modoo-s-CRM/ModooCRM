package com.modoocrm.modoocrm.domain.counseldiary.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;

import java.time.LocalDateTime;

public interface CounselScheduleCustomRepository {

    CounselSchedule findCounselSheduleForClient(Client client, LocalDateTime startTime, LocalDateTime endTime);
}
