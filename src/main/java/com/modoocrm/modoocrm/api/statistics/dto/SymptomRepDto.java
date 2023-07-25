package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SymptomRepDto {
    private List<StatsSymptom> data;

    public class StatsSymptom{
        private String symptom;
        private double ratio;
    }
}
