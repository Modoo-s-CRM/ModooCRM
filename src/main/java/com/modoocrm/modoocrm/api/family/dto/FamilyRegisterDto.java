package com.modoocrm.modoocrm.api.family.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FamilyRegisterDto {

    private String fatherName;

    private String motherName;

    private List<String> children;

    private String familySpecialNote;
}
