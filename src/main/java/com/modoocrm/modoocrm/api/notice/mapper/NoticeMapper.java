package com.modoocrm.modoocrm.api.notice.mapper;

import com.modoocrm.modoocrm.api.notice.dto.NoticeRegisterDto;
import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NoticeMapper {

    public Notice noticeRegisterDtoToNotice(NoticeRegisterDto noticeRegisterDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime noticeDate = LocalDateTime.parse(noticeRegisterDto.getNoticeDate(),formatter);

        return Notice.builder()
                .noticeDate(noticeDate)
                .counselSpecial(noticeRegisterDto.getCounselSpecial())
                .build();
    }
}
