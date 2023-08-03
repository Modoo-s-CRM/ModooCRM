package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.entity.QClient;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientCustomRepository {

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
                        client.firstCounsel.between(startDate, endDate),
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
                        client.firstCounsel.between(startDate, endDate),
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
                        client.firstCounsel.between(startDate, endDate),
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
                        client.firstCounsel.between(startDate, endDate),
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
                        client.firstCounsel.between(startDate, endDate),
                        client.counselType.eq(Client.CounselType.ANTENATAL)
                )
                .fetchCount();
    }

    @Override
    public List<Client> clientsInYear(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(client.firstCounsel.between(startDate, endDate))
                .fetch();
    }

    @Override
    public List<Client> clientsInYearAndCure(LocalDateTime startDate, LocalDateTime endDate) {
        QClient client = QClient.client;
        return queryFactory.selectFrom(client)
                .where(
                        client.firstCounsel.between(startDate, endDate),
                        client.isCure.eq(true)
                )
                .fetch();
    }

    @Override
    public List<Client> searchFilterClient(Client.CounselType transferCounselType, String counselorName, String ageGroup,
                                           String clientGender, String counselProgress, String startDate, String endDate) {
        QClient client = QClient.client;
        return queryFactory
                .selectFrom(client)
                .where(eqCounselType(transferCounselType),
                        eqCounselorName(counselorName),
                        eqAgeGroup(ageGroup),
                        eqClientGender(clientGender),
                        eqCounselProgress(counselProgress),
                        selectDate(startDate,endDate)
                        )
                .fetch();

        //Todo 페이징 처리 로직 , pageNumber는 파라미터로 받아야함.
//        int pageSize = 10;

//        .offset(adjustedPageNumber * pageSize)
//                .limit(pageSize)
//                .fetch();
    }

    private BooleanExpression eqCounselType(Client.CounselType transferCounselType) {
        QClient client = QClient.client;
        if (transferCounselType == null) {
            return null;
        }
        return client.counselType.eq(transferCounselType);
    }

    private BooleanExpression eqCounselorName(String counselorName) {
        QClient client = QClient.client;
        if (StringUtils.isNullOrEmpty(counselorName)) {
            return null;
        }
        return client.counselor.counselorName.eq(counselorName);
    }

    private BooleanExpression eqAgeGroup(String ageGroup) {
        QClient client = QClient.client;
        String ageGroupRange = this.transferAgeGroup(ageGroup);
        if (StringUtils.isNullOrEmpty(ageGroup)){
            return null;
        }
        int startAge = Integer.parseInt(ageGroupRange.split("-")[0]);
        int endAge = Integer.parseInt(ageGroupRange.split("-")[1]);
        return client.age.between(startAge,endAge);
    }

    private BooleanExpression eqClientGender(String clientGender){
        QClient client = QClient.client;
        if (StringUtils.isNullOrEmpty(clientGender)){
            return null;
        }
        return client.clientGender.eq(clientGender);
    }

    private BooleanExpression eqCounselProgress(String counselProgress){
        QClient client = QClient.client;
        if (StringUtils.isNullOrEmpty(counselProgress)){
            return null;
        }
        return client.counselProgress.eq(counselProgress);
    }

    private BooleanExpression selectDate(String startDate, String endDate){
        QClient client = QClient.client;
        if (StringUtils.isNullOrEmpty(startDate) && StringUtils.isNullOrEmpty(endDate)){
            return null;
        }
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        if (!StringUtils.isNullOrEmpty(startDate)) {
            startDateTime = parseStartLocalDateTime(startDate);
        }
        if (!StringUtils.isNullOrEmpty(endDate)) {
            endDateTime = parseEndLocalDateTime(endDate);
        }
        return client.createTime.between(startDateTime,endDateTime);
    }

    private LocalDateTime parseStartLocalDateTime(String dateString) {
        String dateTimeString = dateString + "T00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    private LocalDateTime parseEndLocalDateTime(String dateString){
        String dateTimeString = dateString + "T23:59:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }


    private String transferAgeGroup(String ageGroup) {
        if (ageGroup == null){
            return null;
        }

        switch (ageGroup) {
            case "10대":
                return "10-19";
            case "20대":
                return "20-29";
            case "30대":
                return "30-39";
            case "40대":
                return "40-49";
            case "50대":
                return "50-59";
            case "60대":
                return "60-69";
            case "70대":
                return "70-79";
            case "80대":
                return "80-89";
            default:
                return "유효하지 않는 연령대입니다.";
        }
    }
}
