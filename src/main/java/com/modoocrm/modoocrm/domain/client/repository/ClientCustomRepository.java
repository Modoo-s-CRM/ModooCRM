package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientCustomRepository {

    //월별 총 내담자들 중 counselType이 성인인 카운트
    long adultCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long marriedCoupleCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long coupleCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

    long familyCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long youthCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long antenatalCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

    long groupCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYear(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYearAndCure(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> searchFilterClient(Client.CounselType transferCounselType, String counselorName, String ageGroup,
                                    String clientGender, String counselProgress, String startDate, String endDate);

    List<Client> getThisMonthFirstCounselClient(LocalDateTime startMonth, LocalDateTime endMonth);
}
