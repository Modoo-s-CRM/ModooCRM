package com.modoocrm.modoocrm.api.counselor.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselorSearchResDto {

    private List<CounselorMonthCountDto> monthCountDtos;

    private List<CounselorMonthClientDto> monthClientDtos;

    private List<CounselorEndClientDto> endClientDtos;

    private List<CounselorCureClientDto> cureClientDtos;


}
