package com.modoocrm.modoocrm.domain.notice.service;

import com.modoocrm.modoocrm.api.notice.dto.NoticeResponseDtos;
import com.modoocrm.modoocrm.domain.notice.entity.Notice;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {

    Long registerNotice(Notice registerNotice);

    Long updateNotice(Notice updateNotice,Long noticeId);

    NoticeResponseDtos getNotices(String date);

    List<Notice> getMainNotice(LocalDateTime startTime, LocalDateTime endTime);
}
