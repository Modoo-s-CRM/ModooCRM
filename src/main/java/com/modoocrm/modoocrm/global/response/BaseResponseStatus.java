package com.modoocrm.modoocrm.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다.");

    /**
     * 2000 : Request 오류
     */

    // Common : 2000 ~

    // Calender : 2100 ~

    // Client : 2200 ~

    // CounselDiary : 2300 ~

    // CounselImage : 2400 ~

    // Counselor : 2500 ~

    // Parents : 2600 ~

    // Schedule : 2700 ~

    // Statistics : 2800 ~

    /**
     * 3000 : Response 오류
     */

    // Common : 3000 ~

    // Calender : 3100 ~

    // Client : 3200 ~

    // CounselDiary : 3300 ~

    // CounselImage : 3400 ~

    // Counselor : 3500 ~

    // Parents : 3600 ~

    // Schedule : 3700 ~

    // Statistics : 3800 ~

    /**
     * 4000 : Database, Server 오류
     */

    /**
     * 5000 : Bean Validation 및 기타
     */

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
