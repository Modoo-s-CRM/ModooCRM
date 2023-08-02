package com.modoocrm.modoocrm.api.counselSchedule.mapper;

import com.modoocrm.modoocrm.api.counselSchedule.dto.CounselScheduleRegisterDto;
import com.modoocrm.modoocrm.api.counselSchedule.dto.CounselScheduleRepDto;
import com.modoocrm.modoocrm.api.counselSchedule.dto.CounselScheduleUpdateDto;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselSchedule;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class CounselScheduleMapper {

    public CounselSchedule counselScheduleRegisterDtoToCounselSchedule(
            CounselScheduleRegisterDto counselScheduleRegisterDto) {
        //todo 2023-08-01 예외처리 필요
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime theDayCounselDate = LocalDateTime.parse(counselScheduleRegisterDto.getTheDayCounselDate(), formatter);

        LocalDateTime nextCounselDate = null;
        Optional<String> nextCounselDateStrOpt = Optional.ofNullable(counselScheduleRegisterDto.getNextCounselDate());
        if (nextCounselDateStrOpt.isPresent()) {
            String nextCounselDateStr = nextCounselDateStrOpt.get();
            nextCounselDate = LocalDateTime.parse(nextCounselDateStr, formatter);
        }

        return CounselSchedule.builder()
                .theDayCounselDate(theDayCounselDate)
                .firstCounselCount(counselScheduleRegisterDto.getFirstCounselCount())
                .cureCounselCount(counselScheduleRegisterDto.getCureCounselCount())
                .nextCounselDate(nextCounselDate)
                .note(counselScheduleRegisterDto.getNote())
                .build();
    }

    public CounselSchedule counselScheduleUpdateDtoToCounselChedule(CounselScheduleUpdateDto counselScheduleUpdateDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime theDayCounselDate = LocalDateTime.parse(counselScheduleUpdateDto.getTheDayCounselDate(), formatter);

        LocalDateTime nextCounselDate = null;
        Optional<String> nextCounselDateStrOpt = Optional.ofNullable(counselScheduleUpdateDto.getNextCounselDate());
        if (nextCounselDateStrOpt.isPresent()) {
            String nextCounselDateStr = nextCounselDateStrOpt.get();
            nextCounselDate = LocalDateTime.parse(nextCounselDateStr, formatter);
        }
        return CounselSchedule.builder()
                .theDayCounselDate(theDayCounselDate)
                .firstCounselCount(counselScheduleUpdateDto.getFirstCounselCount())
                .cureCounselCount(counselScheduleUpdateDto.getCureCounselCount())
                .nextCounselDate(nextCounselDate)
                .note(counselScheduleUpdateDto.getNote())
                .build();
    }

    public CounselScheduleRepDto counselScheduleToCounselScheduleRepDto(CounselSchedule counselSchedule){
        if (counselSchedule == null){
            throw new BusinessLogicException(ExceptionCode.COUNSEL_SCHEDULE_NOT_EXIST);
        }
        return CounselScheduleRepDto.builder()
                .theDayCounselDate(counselSchedule.getTheDayCounselDate())
                .firstCounselCount(counselSchedule.getFirstCounselCount())
                .cureCounselCount(counselSchedule.getCureCounselCount())
                .nextCounselDate(counselSchedule.getNextCounselDate())
                .note(counselSchedule.getNote())
                .build();
    }
}
