package com.modoocrm.modoocrm.api.parents.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class ParentsRegisterDto {

    private String fatherName;

    private String motherName;

    @Positive
    private int fatherAge;

    @Positive
    private int motherAge;

    private String fatherEducation;

    private String motherEducation;

    private String fatherJob;

    private String motherJob;

    private String together;
}
