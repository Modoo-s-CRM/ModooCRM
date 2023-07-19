package com.modoocrm.modoocrm.domain.counselor.service;

import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import org.springframework.stereotype.Service;

public interface CounselorService {

    Counselor findVerifiedCounselor(String counselorName);
}
