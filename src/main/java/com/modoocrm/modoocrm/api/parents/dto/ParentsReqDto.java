package com.modoocrm.modoocrm.api.parents.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentsReqDto {

    private String parentsName;

    private int age;

    private String education;

    private String job;

    //동거 여부
    private String together;

}
