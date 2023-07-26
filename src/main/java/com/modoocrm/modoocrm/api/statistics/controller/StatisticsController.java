package com.modoocrm.modoocrm.api.statistics.controller;

import com.modoocrm.modoocrm.api.statistics.dto.CounselTypeRepDto;
import com.modoocrm.modoocrm.api.statistics.dto.CureRepDto;
import com.modoocrm.modoocrm.api.statistics.dto.FirstCounselRepDto;
import com.modoocrm.modoocrm.domain.statistics.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/statistics")
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    //증상별 통계
    @GetMapping("/symptom")
    public ResponseEntity getSymptomStats(@RequestParam("month") String month){
        statisticsService.getSymptomStats(month);
        return null;
    }

    //상담 유형별 통계
    @GetMapping("/type")
    public ResponseEntity getCounselTypeStats(@RequestParam("month") String month){
        CounselTypeRepDto counselTypeRepDto = statisticsService.getCounselTypeStats(month);
         return new ResponseEntity(counselTypeRepDto, HttpStatus.OK);
    }

    //N월 초진 유입수
    @GetMapping("/first-counsel")
    public ResponseEntity getFirstCounselStats(@RequestParam("year") String year){
        FirstCounselRepDto firstCounselRepDto = statisticsService.getFirstCounselStats(year);
        return new ResponseEntity(firstCounselRepDto, HttpStatus.OK);
    }

    //초진에서 치료율
    @GetMapping("/cure")
    public ResponseEntity getCureStats(@RequestParam("year") String year){
        CureRepDto cureRepDto = statisticsService.getCureStats(year);
        return new ResponseEntity(cureRepDto, HttpStatus.OK);
    }
}
