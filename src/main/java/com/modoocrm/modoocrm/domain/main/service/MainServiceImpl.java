package com.modoocrm.modoocrm.domain.main.service;

import com.modoocrm.modoocrm.api.main.dto.MainCounselScheduleDto;
import com.modoocrm.modoocrm.api.main.dto.MainRepDto;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.domain.counseldiary.service.CounselScheduleService;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Transactional
@Service
public class MainServiceImpl implements MainService{

    private final CounselScheduleService counselScheduleService;

    public MainServiceImpl(CounselScheduleService counselScheduleService) {
        this.counselScheduleService = counselScheduleService;
    }

    @Override
    public MainRepDto getMainInfo(String date) {
        this.checkDate(date);
        //받은 date 값으로 상담일정을 먼저 가져오자
        //Todo 값이 많으면 페이징 처리 필요 -> 필요 없음
        LocalDateTime startTime = this.convertToStartDate(date);
        LocalDateTime endTime = this.convertToEndDate(date);

        List<CounselSchedule> counselSchedules = counselScheduleService.getCounselSchedules(startTime,endTime);
        Optional<List<MainCounselScheduleDto>> optionalMainCounselScheduleDtos = Optional.ofNullable(counselSchedules)
                .map(schedules -> schedules.stream()
                        .map(counselSchedule -> MainCounselScheduleDto.builder()
                                .todayCounselDate(counselSchedule.getNextCounselDate())
                                .counselorName(counselSchedule.getClient().getCounselor().getCounselorName())
                                .clientName(counselSchedule.getClient().getClientName())
                                .firstCounselCount(counselSchedule.getFirstCounselCount())
                                .cureCounselCount(counselSchedule.getCureCounselCount())
                                .counselType(counselSchedule.getClient().getCounselType().getCounselTypeDescription())
                                .build()
                        )
                        .sorted(Comparator.comparing(MainCounselScheduleDto::getTodayCounselDate))
                        .collect(Collectors.toList())
                );

        List<MainCounselScheduleDto> mainCounselScheduleDtos = optionalMainCounselScheduleDtos
                .orElseGet(() -> Collections.singletonList(MainCounselScheduleDto.emptySchedule()));


        //받은 date 값으로 공지 가져오기

        return null;
    }


    private void checkDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            throw new BusinessLogicException(ExceptionCode.INVALID_REQUEST_DATE);
        }
    }

    private LocalDateTime convertToStartDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 00:00:00", formatter);
    }

    private LocalDateTime convertToEndDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 23:59:59", formatter);
    }
}
