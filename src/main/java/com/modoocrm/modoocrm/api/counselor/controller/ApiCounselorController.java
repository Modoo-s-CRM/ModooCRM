package com.modoocrm.modoocrm.api.counselor.controller;

import com.modoocrm.modoocrm.api.counselor.dto.CounselorSearchReqDto;
import com.modoocrm.modoocrm.api.counselor.dto.CounselorSearchResDto;
import com.modoocrm.modoocrm.api.counselor.service.ApiCounselorService;
import com.modoocrm.modoocrm.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/counselor")
public class ApiCounselorController {

    private final ApiCounselorService apiCounselorService;

    @GetMapping("/")
    public BaseResponse<List<CounselorSearchResDto>> searchCounselor(
            @RequestBody CounselorSearchReqDto counselorSearchReq) {
        return new BaseResponse<>(apiCounselorService.searchCounselor(counselorSearchReq));

    }
}
