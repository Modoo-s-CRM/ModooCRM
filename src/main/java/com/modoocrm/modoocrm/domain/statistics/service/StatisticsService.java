package com.modoocrm.modoocrm.domain.statistics.service;

import com.modoocrm.modoocrm.api.statistics.dto.*;

public interface StatisticsService {

    SymptomRepDto getSymptomStats(String month);

    CounselTypeRepDto getCounselTypeStats(String month);

    FirstCounselRepDto getFirstCounselStats(String year);

    CureRepDto getCureStats(String year);

    JobRepDto getJobStats(String month);
}
