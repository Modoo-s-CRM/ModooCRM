package com.modoocrm.modoocrm.api.parents.mapper;

import com.modoocrm.modoocrm.api.parents.dto.ParentsRegisterDto;
import com.modoocrm.modoocrm.api.parents.dto.ParentsResponseDto;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import org.springframework.stereotype.Component;

@Component
public class ParentsMapper {
    public Parents parentsRegisterDtoToParents(ParentsRegisterDto parentsRegisterDto){
        return Parents.builder()
                .fatherName(parentsRegisterDto.getFatherName())
                .motherName(parentsRegisterDto.getMotherName())
                .fatherAge(parentsRegisterDto.getFatherAge())
                .motherAge(parentsRegisterDto.getMotherAge())
                .fatherEducationInfo(parentsRegisterDto.getFatherEducation())
                .motherEducationInfo(parentsRegisterDto.getMotherEducation())
                .fatherJob(parentsRegisterDto.getFatherJob())
                .motherJob(parentsRegisterDto.getMotherJob())
                .together(parentsRegisterDto.getTogether())
                .build();
    }

    public ParentsResponseDto parentsToParentsResponseDto(Parents parents){
        return new ParentsResponseDto(
                parents.getFatherName(),
                parents.getMotherName(),
                parents.getFatherAge(),
                parents.getMotherAge(),
                parents.getFatherEducationInfo(),
                parents.getMotherEducationInfo(),
                parents.getFatherJob(),
                parents.getMotherJob(),
                parents.getTogether()
        );
    }
}
