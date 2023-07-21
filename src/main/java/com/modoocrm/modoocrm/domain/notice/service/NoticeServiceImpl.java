package com.modoocrm.modoocrm.domain.notice.service;

import com.modoocrm.modoocrm.api.notice.dto.NoticeRepDto;
import com.modoocrm.modoocrm.api.notice.dto.NoticeResponseDtos;
import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import com.modoocrm.modoocrm.domain.notice.repository.NoticeRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Long registerNotice(Notice registerNotice) {
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
        LocalDate parameterDate = LocalDate.parse(date);

        //todo 오늘 날짜 기준 이전 날짜가 들어오면 예외 발생

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

    private Notice findVerifiedNotice(Long noticeId){
        return noticeRepository.findById(noticeId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.NOTICE_NOT_FOUND));
    }
}
