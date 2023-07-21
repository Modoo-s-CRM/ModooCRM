package com.modoocrm.modoocrm.domain.notice.service;

import com.modoocrm.modoocrm.domain.notice.entity.Notice;

public interface NoticeService {

    Long registerNotice(Notice registerNotice);

    Long updateNotice(Notice updateNotice,Long noticeId);
}
