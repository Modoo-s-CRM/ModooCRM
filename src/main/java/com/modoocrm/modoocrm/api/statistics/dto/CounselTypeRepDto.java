package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CounselTypeRepDto {
    private List<StatsCounselType> data;

    @Setter @Getter
    public static class StatsCounselType{
        private String counselType;
        private double ratio;

        @Builder
        public StatsCounselType(String counselType, double ratio){
            this.counselType = counselType;
            this.ratio = ratio;
        }
    }
}
