package com.modoocrm.modoocrm.domain.counseldiary.repository;

import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselScheduleRepository extends JpaRepository<CounselSchedule,Long>, CounselScheduleCustomRepository{
}
