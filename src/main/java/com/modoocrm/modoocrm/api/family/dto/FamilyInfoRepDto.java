package com.modoocrm.modoocrm.api.family.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FamilyInfoRepDto {

    private List<FamilyMember> familyMembers;

    private String houseHolder;
    private String familySpecialNote;

    @Getter @Setter @NoArgsConstructor
    public static class FamilyMember{
        private String name;
        private int age;
        private String phone;
        private String job;

    }
}
