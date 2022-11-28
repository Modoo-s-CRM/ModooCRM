package com.modoocrm.modoocrm.api.counselor.service;

import com.modoocrm.modoocrm.api.counselor.dto.CounselorSearchReqDto;
import com.modoocrm.modoocrm.api.counselor.dto.CounselorSearchResDto;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.counselor.service.CounselorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiCounselorService {

    private final CounselorService counselorService;

    public List<CounselorSearchResDto> searchCounselor(CounselorSearchReqDto counselorSearchReqDto){
//        List<Counselor> allCounselor = counselorService
        return null;

    }

}
