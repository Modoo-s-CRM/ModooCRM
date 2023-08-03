package com.modoocrm.modoocrm.domain.counseldiary.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;

import java.time.LocalDateTime;
import java.util.List;

public interface CounselScheduleCustomRepository {

    CounselSchedule findCounselSheduleForClient(Client client, LocalDateTime startTime, LocalDateTime endTime);

    List<CounselSchedule> findCounselSchedule(LocalDateTime startTime, LocalDateTime endTime);
}
