package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.entity.QClient;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ClientRepositoryImpl implements ClientCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ClientRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    //월별 총 내담자들 중 counselType이 성인인 카운트
    @Override
    public long adultCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate,endDate),
                        client.counselType.eq(Client.CounselType.ADULT)
                )
                .fetchCount();
    }

    @Override
    public long marriedCoupleCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate, endDate),
                        client.counselType.eq(Client.CounselType.MARRIED_COUPLE)
                )
                .fetchCount();

    }

    @Override
    public long coupleCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate,endDate),
                        client.counselType.eq(Client.CounselType.COUPLE)
                )
                .fetchCount();
    }

    @Override
    public long familyCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate,endDate),
                        client.counselType.eq(Client.CounselType.FAMILY)
                )
                .fetchCount();
    }

    @Override
    public long youthCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate,endDate),
                        client.counselType.eq(Client.CounselType.YOUTH)
                )
                .fetchCount();
    }

    @Override
    public long antenatalCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate,endDate),
                        client.counselType.eq(Client.CounselType.ANTENATAL)
                )
                .fetchCount();
    }
}
