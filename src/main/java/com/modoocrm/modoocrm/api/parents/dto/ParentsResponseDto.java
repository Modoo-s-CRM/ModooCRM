package com.modoocrm.modoocrm.api.parents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public class ParentsResponseDto {

    private String fatherName;

    private String motherName;

    private int fatherAge;

    private int motherAge;

    private String fatherEducation;

    private String motherEducation;

    private String fatherJob;

    private String motherJob;

    private String together;
}
