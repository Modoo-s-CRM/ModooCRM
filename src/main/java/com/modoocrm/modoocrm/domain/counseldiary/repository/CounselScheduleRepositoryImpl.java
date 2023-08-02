package com.modoocrm.modoocrm.domain.counseldiary.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.domain.counseldiary.entity.QCounselSchedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class CounselScheduleRepositoryImpl implements CounselScheduleCustomRepository{

    private final JPAQueryFactory queryFactory;

    public CounselScheduleRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public CounselSchedule findCounselSheduleForClient(Client client, LocalDateTime startTime, LocalDateTime endTime) {
        QCounselSchedule cs = QCounselSchedule.counselSchedule;

        return queryFactory
                .selectFrom(cs)
                .where(
                        cs.client.eq(client),
                        cs.theDayCounselDate.between(startTime, endTime)
                        )
                .fetchOne();
    }
}
