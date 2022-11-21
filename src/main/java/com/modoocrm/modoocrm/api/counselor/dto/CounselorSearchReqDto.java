package com.modoocrm.modoocrm.api.counselor.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselorSearchReqDto {

    private Integer date;

    private String counselorName;

    private String counselType;

}
