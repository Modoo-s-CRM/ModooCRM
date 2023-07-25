package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CounselTypeRepDto {
    private List<StatsCounselType> data;

    public class StatsCounselType{
        private String counselType;
        private double ratio;
    }
}
