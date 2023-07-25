package com.modoocrm.modoocrm.domain.statistics.service;

import com.modoocrm.modoocrm.api.statistics.dto.CounselTypeRepDto;
import com.modoocrm.modoocrm.api.statistics.dto.SymptomRepDto;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@Service
public class StatisticsServiceImpl implements StatisticsService{

    private final ClientService clientService;

    public StatisticsServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SymptomRepDto getSymptomStats(String month) {
        //todo 증상 카운트 / 월별 총 내담자 수(내담자 수의 기준 : firstCounsel)

        // month를 LocalDate로 바꾼다.
        LocalDateTime startDate = this.monthToStartDate(month);
        LocalDateTime endDate = this.monthToEndDate(month);
        System.out.println(startDate);
        System.out.println(endDate);
        //월별 총 내담자 수
        int monthClientCount = clientService.monthFirstCounselCount(startDate, endDate);

        //증상 가져오기 -> month에 해당하면서

        //우울증

        //불안

        //스트레스

        //공황

        return null;
    }

    @Override
    public CounselTypeRepDto getCounselTypeStats(String month) {
        LocalDateTime startDate = this.monthToStartDate(month);
        LocalDateTime endDate = this.monthToEndDate(month);
        System.out.println("시작일" + startDate);
        System.out.println("마지막일" + endDate);
        //월별 총 내담자 수
        int monthClientCount = clientService.monthFirstCounselCount(startDate,endDate);

        //성인 -> 수

        //부부

        //커플

        //가족

        //아청

        //태교
        return null;
    }

    //문자열인 month를 2023-xx-01 로 파싱(시작일)
    private LocalDateTime monthToStartDate(String month){
        int year = Integer.parseInt(month.split("-")[0]);
        int targetMonth = Integer.parseInt(month.split("0")[1]);

        return LocalDateTime.of(year,targetMonth, 1, 0, 0, 0);
    }

    //문자열인 month를 2023-xx-xx 로 파싱(마지막일)
    private LocalDateTime monthToEndDate(String month){
        int year = Integer.parseInt(month.split("-")[0]);
        int targetMonth = Integer.parseInt(month.split("0")[1]);

        LocalDateTime startDate = LocalDateTime.of(year,targetMonth, 1, 0, 0, 0);
        int lastDayOfMonth = startDate.toLocalDate().withDayOfMonth(startDate.toLocalDate().lengthOfMonth()).getDayOfMonth();
        return LocalDateTime.of(year,targetMonth,lastDayOfMonth,0,0,0);
    }
}
