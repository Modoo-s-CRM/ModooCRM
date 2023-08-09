package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SymptomRepDto {
    private List<StatsSymptom> data;

    @Getter
    public static class StatsSymptom{
        private String symptomGrade;
        private double ratio;

        @Builder
        public StatsSymptom(String symptomGrade, double ratio) {
            this.symptomGrade = symptomGrade;
            this.ratio = ratio;
        }
    }
}
