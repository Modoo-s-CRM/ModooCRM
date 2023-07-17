package com.modoocrm.modoocrm.api.parents.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

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
