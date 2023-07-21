package com.modoocrm.modoocrm.api.notice.controller;

import com.modoocrm.modoocrm.api.notice.dto.NoticeRegisterDto;
import com.modoocrm.modoocrm.api.notice.mapper.NoticeMapper;
import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import com.modoocrm.modoocrm.domain.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RequestMapping("/api/notice")
@RestController
public class NoticeContrller {

    private final NoticeMapper noticeMapper;
    private final NoticeService noticeService;

    public NoticeContrller(NoticeMapper noticeMapper, NoticeService noticeService) {
        this.noticeMapper = noticeMapper;
        this.noticeService = noticeService;
    }

    @PostMapping
    public ResponseEntity registerNotice(@RequestBody NoticeRegisterDto noticeRegisterDto){
        Notice registerNotice = noticeMapper.noticeRegisterDtoToNotice(noticeRegisterDto);
        Long saveNoticeId = noticeService.registerNotice(registerNotice);
        return new ResponseEntity(saveNoticeId, HttpStatus.CREATED);
    }

    @PatchMapping("/{notice-id}")
    public ResponseEntity updateNotice(@Valid @RequestBody NoticeRegisterDto noticeRegisterDto,
                                       @Positive @PathVariable("notice-id") Long noticeId){
        Notice updateNotice = noticeMapper.noticeRegisterDtoToNotice(noticeRegisterDto);
        Long updateNoticeId = noticeService.updateNotice(updateNotice,noticeId);
        return new ResponseEntity(updateNoticeId,HttpStatus.ACCEPTED);
    }

}
