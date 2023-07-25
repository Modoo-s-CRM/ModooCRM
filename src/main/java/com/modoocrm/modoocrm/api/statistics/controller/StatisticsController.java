package com.modoocrm.modoocrm.api.statistics.controller;

import com.modoocrm.modoocrm.api.statistics.dto.CounselTypeRepDto;
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

    @GetMapping("/symptom")
    public ResponseEntity getSymptomStats(@RequestParam("month") String month){
        statisticsService.getSymptomStats(month);
        return null;
    }

    @GetMapping("/type")
    public ResponseEntity getCounselTypeStats(@RequestParam("month") String month){
        CounselTypeRepDto counselTypeRepDto = statisticsService.getCounselTypeStats(month);
         return new ResponseEntity(counselTypeRepDto, HttpStatus.OK);
    }
}
