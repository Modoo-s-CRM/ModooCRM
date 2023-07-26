package com.modoocrm.modoocrm.domain.statistics.service;

import com.modoocrm.modoocrm.api.statistics.dto.CounselTypeRepDto;
import com.modoocrm.modoocrm.api.statistics.dto.FirstCounselRepDto;
import com.modoocrm.modoocrm.api.statistics.dto.SymptomRepDto;

public interface StatisticsService {

    SymptomRepDto getSymptomStats(String month);

    CounselTypeRepDto getCounselTypeStats(String month);

    FirstCounselRepDto getFirstCounselStats(String year);
}
