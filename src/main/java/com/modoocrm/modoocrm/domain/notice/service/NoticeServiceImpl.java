package com.modoocrm.modoocrm.domain.notice.service;

import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import com.modoocrm.modoocrm.domain.notice.repository.NoticeRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    private Notice findVerifiedNotice(Long noticeId){
        return noticeRepository.findById(noticeId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.NOTICE_NOT_FOUND));
    }
}
