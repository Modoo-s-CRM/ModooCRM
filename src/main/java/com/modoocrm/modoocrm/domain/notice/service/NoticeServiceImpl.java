package com.modoocrm.modoocrm.domain.notice.service;

import com.modoocrm.modoocrm.api.notice.dto.NoticeRepDto;
import com.modoocrm.modoocrm.api.notice.dto.NoticeResponseDtos;
import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import com.modoocrm.modoocrm.domain.notice.repository.NoticeRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    //Todo 스케줄러 삭제는 일주일정도로 수정해야함. -> 이야기 나눠볼 필요
    @Override
    public Long registerNotice(Notice registerNotice) {
        this.checkParameterDate(registerNotice.getNoticeDate());
        //등록일 설정
        registerNotice.setSafeNoticeDate(LocalDate.now());
        //삭제 예정일 설정
        registerNotice.setDeleteDate(LocalDate.now().plusDays(3));

        Notice saveNotice = noticeRepository.save(registerNotice);
        return saveNotice.getNoticeId();
    }

    @Override
    public Long updateNotice(Notice updateNotice, Long noticeId) {
        Notice findNotice = findVerifiedNotice(noticeId);

        Optional.ofNullable(updateNotice.getNoticeDate())
                .ifPresent(noticeDate -> findNotice.setNoticeDate(noticeDate));
        Optional.ofNullable(updateNotice.getCounselSpecial())
                .ifPresent(counselSpecial -> findNotice.setCounselSpecial(counselSpecial));

        Notice successUpdateNotice = noticeRepository.save(findNotice);
        return successUpdateNotice.getNoticeId();
    }

    @Override
    public NoticeResponseDtos getNotices(String date) {
        this.checkDate(date);
        LocalDate parameterDate = LocalDate.parse(date);

        //todo 오늘 날짜 기준 이전 날짜가 들어오면 예외 발생

        //todo 리팩토링 필요
        LocalDateTime startDateTime = parameterDate.atStartOfDay();
        LocalDateTime endDateTime = parameterDate.atTime(23,59,59);

        List<Notice> getDateNotice = noticeRepository.findByNoticeDateBetween(startDateTime,endDateTime);

        //ID값 추출
        List<Long> noticeIds = getDateNotice.stream()
                .map(Notice::getNoticeId)
                .collect(Collectors.toList());

        //시분 정보만 추출하여 문자열로 반환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        List<String> noticeTimeList = getDateNotice.stream()
                .map(notice -> notice.getNoticeDate().format(formatter))
                .collect(Collectors.toList());

        //counselSpecial은 그대로
        List<String> counselSpecialList = getDateNotice.stream()
                .map(Notice::getCounselSpecial)
                .collect(Collectors.toList());

        NoticeResponseDtos noticeResponseDtos = new NoticeResponseDtos();

        List<NoticeRepDto> noticeRepDtos = new ArrayList<>();
        for (int i=0; i<noticeIds.size(); i++){
            NoticeRepDto noticeRepDto = new NoticeRepDto();
            noticeRepDto.setNoticeId(noticeIds.get(i));
            noticeRepDto.setNoticeTime(noticeTimeList.get(i));
            noticeRepDto.setCounselSpecial(counselSpecialList.get(i));

            noticeRepDtos.add(noticeRepDto);
        }

        noticeResponseDtos.setData(noticeRepDtos);
        return noticeResponseDtos;
    }

    @Override
    public List<Notice> getMainNotice(LocalDateTime startTime, LocalDateTime endTime) {
        return noticeRepository.findByNoticeDateBetween(startTime, endTime);
    }

    private Notice findVerifiedNotice(Long noticeId){
        return noticeRepository.findById(noticeId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.NOTICE_NOT_FOUND));
    }

    private void checkDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            throw new BusinessLogicException(ExceptionCode.INVALID_REQUEST_DATE);
        }
    }

    //공지 자동 삭제 - 스프링 스케줄링
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredNotices(){
        LocalDate today = LocalDate.now();
        // DB에서 삭제 예정일이 지난 공지를 조회
        List<Notice> expiredNotices = noticeRepository.findByDeleteDateBefore(today);

        //조회된 공지 삭제
        noticeRepository.deleteAll(expiredNotices);
    }

    //오늘 날짜 기준 이전보다
    private void checkParameterDate(LocalDateTime parameterDate){
        LocalDateTime today = LocalDateTime.now();
        if (parameterDate.isBefore(today)){
            throw new BusinessLogicException(ExceptionCode.INVALID_DATE);
        }
    }
}
