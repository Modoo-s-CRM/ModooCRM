package com.modoocrm.modoocrm.domain.client.repository;

import java.time.LocalDateTime;

public interface ClientCustomRepository {

    //월별 총 내담자들 중 counselType이 성인인 카운트
    long  adultCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  marriedCoupleCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  coupleCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

    long familyCounselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  youthCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);
    long  antenatalCouselTypeCount(LocalDateTime startDate, LocalDateTime endDate);

}
