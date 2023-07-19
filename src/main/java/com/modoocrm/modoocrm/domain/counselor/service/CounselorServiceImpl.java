package com.modoocrm.modoocrm.domain.counselor.service;

import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.counselor.repository.CounselorRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
public class CounselorServiceImpl implements CounselorService {

    private final CounselorRepository counselorRepository;

    public CounselorServiceImpl(CounselorRepository counselorRepository) {
        this.counselorRepository = counselorRepository;
    }

    //상담사 찾기
    @Override
    public Counselor findVerifiedCounselor(String counselorName) {
        Counselor findCounselor = counselorRepository.findByCounselorName(counselorName).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COUNSELOR_NOT_FOUND));
        return findCounselor;
    }
}

