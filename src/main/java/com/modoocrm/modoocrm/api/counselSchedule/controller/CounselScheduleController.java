package com.modoocrm.modoocrm.api.counselSchedule.controller;

import com.modoocrm.modoocrm.api.counselSchedule.dto.CounselScheduleRegisterDto;
import com.modoocrm.modoocrm.api.counselSchedule.dto.CounselScheduleUpdateDto;
import com.modoocrm.modoocrm.api.counselSchedule.mapper.CounselScheduleMapper;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.domain.counseldiary.service.CounselScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RequestMapping("/api/client/schedule")
@RestController
public class CounselScheduleController {

    private final CounselScheduleMapper counselScheduleMapper;
    private final CounselScheduleService counselScheduleService;

    public CounselScheduleController(CounselScheduleMapper counselScheduleMapper, CounselScheduleService counselScheduleService) {
        this.counselScheduleMapper = counselScheduleMapper;
        this.counselScheduleService = counselScheduleService;
    }

    @PostMapping("/{client-id}")
    public ResponseEntity registerCounselSchedule(@Positive @PathVariable("client-id") Long clientId,
                                                  @Valid @RequestBody CounselScheduleRegisterDto counselScheduleRegisterDto){
        CounselSchedule counselSchedule = counselScheduleMapper.counselScheduleRegisterDtoToCounselSchedule(counselScheduleRegisterDto);
        counselScheduleService.registerCounselSchedule(clientId,counselSchedule);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{counsel-schedule-id}")
    public ResponseEntity updateCounselSchedule(@Positive @PathVariable("counsel-schedule-id") Long counselScheduleId,
                                                @Valid @RequestBody CounselScheduleUpdateDto counselScheduleUpdateDto){
        CounselSchedule counselSchedule = counselScheduleMapper.counselScheduleUpdateDtoToCounselChedule(counselScheduleUpdateDto);
        counselScheduleService.updateCounselSchedule(counselScheduleId, counselSchedule);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
