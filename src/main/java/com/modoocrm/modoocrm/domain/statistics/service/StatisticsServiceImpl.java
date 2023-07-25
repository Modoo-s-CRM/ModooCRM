package com.modoocrm.modoocrm.domain.statistics.service;

import com.modoocrm.modoocrm.api.statistics.dto.CounselTypeRepDto;
import com.modoocrm.modoocrm.api.statistics.dto.SymptomRepDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        //월별 총 내담자 수
        int monthClientCount = clientService.monthFirstCounselCount(startDate,endDate);

        //월별 총 내담자들 중 counselType이 성인인 카운
        int adultCount = clientService.adultCount(startDate,endDate);
        double adultPercentage = this.percentage(adultCount, monthClientCount);

        //부부
        int marriedCoupleCount = clientService.marriedCoupleCount(startDate,endDate);
        double marriedCouplePercentage = this.percentage(marriedCoupleCount,monthClientCount);

        //커플
        int coupleCount = clientService.coupleCount(startDate,endDate);
        double couplePercentage = this.percentage(coupleCount,monthClientCount);

        //가족
        int familyCount = clientService.familyCount(startDate,endDate);
        double familyPercentage = this.percentage(familyCount,monthClientCount);

        //아청
        int youthCount = clientService.youthCount(startDate,endDate);
        double youthPercentage = this.percentage(youthCount,monthClientCount);

        //태교
        int antenatalCount = clientService.antenatalCount(startDate,endDate);
        double antenatalPercentage = this.percentage(antenatalCount,monthClientCount);


        //todo 이놈의 반복되는 코드들 리팩토링 필수!
        CounselTypeRepDto counselTypeRepDto = new CounselTypeRepDto();

        List<CounselTypeRepDto.StatsCounselType> statsCounselTypes = new ArrayList<>();
        CounselTypeRepDto.StatsCounselType adult = CounselTypeRepDto.StatsCounselType.builder()
                .counselType(Client.CounselType.ADULT.getCounselTypeDescription())
                .ratio(adultPercentage)
                .build();
        statsCounselTypes.add(adult);

        CounselTypeRepDto.StatsCounselType marriedCouple = CounselTypeRepDto.StatsCounselType.builder()
                .counselType(Client.CounselType.MARRIED_COUPLE.getCounselTypeDescription())
                .ratio(marriedCouplePercentage)
                .build();
        statsCounselTypes.add(marriedCouple);

        CounselTypeRepDto.StatsCounselType couple = CounselTypeRepDto.StatsCounselType.builder()
                .counselType(Client.CounselType.COUPLE.getCounselTypeDescription())
                .ratio(couplePercentage)
                .build();
        statsCounselTypes.add(couple);

        CounselTypeRepDto.StatsCounselType family = CounselTypeRepDto.StatsCounselType.builder()
                .counselType(Client.CounselType.FAMILY.getCounselTypeDescription())
                .ratio(familyPercentage)
                .build();
        statsCounselTypes.add(family);

        CounselTypeRepDto.StatsCounselType youth = CounselTypeRepDto.StatsCounselType.builder()
                .counselType(Client.CounselType.YOUTH.getCounselTypeDescription())
                .ratio(youthPercentage)
                .build();
        statsCounselTypes.add(youth);

        CounselTypeRepDto.StatsCounselType antenatal = CounselTypeRepDto.StatsCounselType.builder()
                .counselType(Client.CounselType.ANTENATAL.getCounselTypeDescription())
                .ratio(antenatalPercentage)
                .build();
        statsCounselTypes.add(antenatal);

        counselTypeRepDto.setData(statsCounselTypes);
        return counselTypeRepDto;
    }

    private CounselTypeRepDto.StatsCounselType createStatsCounselType(String counselTypeDescription, double ratio){
        return CounselTypeRepDto.StatsCounselType.builder()
                .counselType(counselTypeDescription)
                .ratio(ratio)
                .build();
    }

    private double percentage(int anyCount,int monthClientCount){
        double ratio = (double)anyCount / monthClientCount * 100;
        return (int) Math.round(ratio);
    }

    //문자열인 month를 2023-xx-01 로 파싱(시작일)
    private LocalDateTime monthToStartDate(String month){
        int year = Integer.parseInt(month.split("-")[0]);
        int targetMonth = Integer.parseInt(month.split("-")[1]);

        return LocalDateTime.of(year,targetMonth, 1, 0, 0, 0);
    }

    //문자열인 month를 2023-xx-xx 로 파싱(마지막일)
    private LocalDateTime monthToEndDate(String month){
        int year = Integer.parseInt(month.split("-")[0]);
        int targetMonth = Integer.parseInt(month.split("-")[1]);

        LocalDateTime startDate = LocalDateTime.of(year,targetMonth, 1, 0, 0, 0);
        int lastDayOfMonth = startDate.toLocalDate().withDayOfMonth(startDate.toLocalDate().lengthOfMonth()).getDayOfMonth();
        return LocalDateTime.of(year,targetMonth,lastDayOfMonth,0,0,0);
    }
}
