package com.modoocrm.modoocrm.domain.main.service;

import com.modoocrm.modoocrm.api.main.dto.*;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.domain.counseldiary.service.CounselScheduleService;
import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import com.modoocrm.modoocrm.domain.notice.service.NoticeService;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final NoticeService noticeService;
    private final ClientService clientService;

    public MainServiceImpl(CounselScheduleService counselScheduleService, NoticeService noticeService, ClientService clientService) {
        this.counselScheduleService = counselScheduleService;
        this.noticeService = noticeService;
        this.clientService = clientService;
    }

    @Override
    public MainRepDto getMainInfo(String date) { //date 형식 : 2022-10-11
        this.checkDate(date);
        //받은 date 값으로 상담일정을 먼저 가져오자
        //Todo 값이 많으면 페이징 처리 필요 -> 필요 없음, 휠 처리
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
        List<Notice> noticeList = noticeService.getMainNotice(startTime, endTime);
        Optional<List<MainNoticeDto>> optionalMainNoticeDtos = Optional.ofNullable(noticeList)
                .map(notices -> notices.stream()
                        .map(notice -> MainNoticeDto.builder()
                                .noticeDate(notice.getNoticeDate())
                                .counselSpecial(notice.getCounselSpecial())
                                .build()
                        )
                        .sorted(Comparator.comparing(MainNoticeDto::getNoticeDate))
                        .collect(Collectors.toList())
                );
        List<MainNoticeDto> mainNoticeDtos = optionalMainNoticeDtos
                .orElseGet(() -> Collections.singletonList(MainNoticeDto.emptyNoticeDto()));

        //date에 해당하는 월 초진 내담자 정보와 상담 유형 가져오기
        LocalDateTime startMonth = this.convertToStartMonth(date);
        LocalDateTime endMonth = this.convertToEndMonth(date);
        List<Client> clientList = clientService.getThisMonthFirstCounselClient(startMonth,endMonth);
        Optional<List<MainThisMonthClientDto>> optionalMainThisMonthClientDtos = Optional.ofNullable(clientList)
                .map(clients ->clients.stream()
                                .map(client -> MainThisMonthClientDto.builder()
                                        .clientName(client.getClientName())
                                        .counselType(client.getCounselType().getCounselTypeDescription())
                                        .build())
                                .collect(Collectors.toList())
                        );
        List<MainThisMonthClientDto> mainThisMonthClientDtos = optionalMainThisMonthClientDtos
                .orElseGet(() -> Collections.singletonList(MainThisMonthClientDto.emptyMainThisMonthClientDto()));

        //Todo date에 해당하는 월의 다음 달 초진 내담자 정보와 상담 유형 가져오기
        LocalDateTime startNextMonth = this.convertToStartMonth(date).plusMonths(1);
        LocalDateTime endNextMonth = this.convertToEndMonth(date).plusMonths(1);
        List<Client> nextClientList = clientService.getThisMonthFirstCounselClient(startNextMonth, endNextMonth);
        Optional<List<MainNextMonthClientDto>> optionalMainNextMonthClientDtos = Optional.ofNullable(nextClientList)
                .map(clients -> clients.stream()
                        .map(client -> MainNextMonthClientDto.builder()
                                .clientName(client.getClientName())
                                .counselType(client.getCounselType().getCounselTypeDescription())
                                .build())
                        .collect(Collectors.toList())
                );
        List<MainNextMonthClientDto> nextMonthClientDtos = optionalMainNextMonthClientDtos
                .orElseGet(() -> Collections.singletonList(MainNextMonthClientDto.emptyMainNextMonthClientDto()));

        return MainRepDto.builder()
                .counselSchedules(mainCounselScheduleDtos)
                .notices(mainNoticeDtos)
                .thisMonthClients(mainThisMonthClientDtos)
                .nextMonthClients(nextMonthClientDtos)
                .build();
    }


    private void checkDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            throw new BusinessLogicException(ExceptionCode.INVALID_REQUEST_DATE);
        }
    }

    //시분초가 붙는 LocalDateTime 형식 ex) 2023-07-31 12:30:00
    private LocalDateTime convertToStartDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 00:00:00", formatter);
    }

    private LocalDateTime convertToEndDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " 23:59:59", formatter);
    }

    //시분초가 0인 LocalDateTime 형식 ex) 2023-07-31 00:00:00
    private LocalDateTime convertToStartMonth(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date + " 00:00:00", formatter);
        return dateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
    }

    private LocalDateTime convertToEndMonth(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,formatter);
        LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        LocalDateTime endOfMonth = lastDayOfMonth.atTime(LocalTime.MAX);
        return endOfMonth;
    }
}
