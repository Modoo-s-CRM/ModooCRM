package com.modoocrm.modoocrm.domain.statistics.service;

import com.modoocrm.modoocrm.api.statistics.dto.*;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public FirstCounselRepDto getFirstCounselStats(String year) {
        int yearValue = Integer.parseInt(year.replaceAll("[^0-9]",""));
        LocalDateTime startOfMonth = LocalDateTime.of(yearValue, Month.JANUARY,1,0,0);
        LocalDateTime endOfMonth = LocalDateTime.of(yearValue, Month.DECEMBER, 31, 0, 0);
        //연도와 맞는 모든 내담자의 데이터들을 가지고 온다.
        List<Client> clientsInYear = clientService.clientsInYear(startOfMonth, endOfMonth);
        //가지고 온 데이터를 월별로 그룹핑
        List<FirstCounselRepDto.FirstCounselStats> firstCounselStateList = clientsInYear.stream()
                .collect(Collectors.groupingBy(
                        client -> client.getFirstCounsel().getMonth(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    String monthName = this.getKoreanMonthName(entry.getKey());
                            //Month.of(entry.getKey().getValue()).name();
                    FirstCounselRepDto.FirstCounselStats firstCounselStats = FirstCounselRepDto.FirstCounselStats.builder()
                            .month(monthName)
                            .visit(Math.toIntExact(entry.getValue()))
                            .build();
                    return firstCounselStats;
                }
                )
                .collect(Collectors.toList());

        FirstCounselRepDto firstCounselRepDto = new FirstCounselRepDto();
        firstCounselRepDto.setData(firstCounselStateList);

        return firstCounselRepDto;
    }

    @Override
    public CureRepDto getCureStats(String year) {
        int yearValue = Integer.parseInt(year.replaceAll("[^0-9]",""));
        LocalDateTime startOfMonth = LocalDateTime.of(yearValue, Month.JANUARY,1,0,0);
        LocalDateTime endOfMonth = LocalDateTime.of(yearValue, Month.DECEMBER, 31, 0, 0);

        List<Client> clients = clientService.clientsInYearAndCure(startOfMonth,endOfMonth);

        List<CureRepDto.CureStats> cureStatsList = clients.stream()
                .collect(Collectors.groupingBy(
                        client -> client.getFirstCounsel().getMonth(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    String monthName = this.getKoreanMonthName(entry.getKey());
                    CureRepDto.CureStats cureStats = CureRepDto.CureStats.builder()
                            .month(monthName)
                            .cure(Math.toIntExact(entry.getValue()))
                            .build();
                    return cureStats;
                        })
                .collect(Collectors.toList());
        CureRepDto cureRepDto = new CureRepDto();
        cureRepDto.setData(cureStatsList);

        return cureRepDto;
    }

    @Override
    public JobRepDto getJobStats(String month) {
        return null;
    }

    private double percentage(int anyCount, int monthClientCount){
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

    //한국어 월 이름 반환 메서드
    private String getKoreanMonthName(Month month) {
        switch (month) {
            case JANUARY:
                return "1월";
            case FEBRUARY:
                return "2월";
            case MARCH:
                return "3월";
            case APRIL:
                return "4월";
            case MAY:
                return "5월";
            case JUNE:
                return "6월";
            case JULY:
                return "7월";
            case AUGUST:
                return "8월";
            case SEPTEMBER:
                return "9월";
            case OCTOBER:
                return "10월";
            case NOVEMBER:
                return "11월";
            case DECEMBER:
                return "12월";
            default:
                throw new IllegalArgumentException("잘못된 월 입력");
        }
    }
}
