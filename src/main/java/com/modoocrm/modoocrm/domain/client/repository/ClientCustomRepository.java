package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientCustomRepository {

    //월별 총 내담자들 중 counselType이 성인인 카운트
    long  adultCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  marriedCoupleCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  coupleCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

    long familyCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  youthCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  antenatalCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYear(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYearAndCure(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> searchFilterClient(Client.CounselType transferCounselType, String counselorName, String ageGroup,
                                    String clientGender, String counselProgress, String startDate, String endDate);

    List<Client> getThisMonthFristCounselClient(LocalDateTime startMonth, LocalDateTime endMonth);
}
