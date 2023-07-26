package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FirstCounselRepDto {

    private List<FirstCounselStats> data;

    @Getter @Setter
    public static class FirstCounselStats{
        private String month;
        private int visit;

        @Builder
        public FirstCounselStats(String month, int visit){
            this.month = month;
            this.visit = visit;
        }
    }
}
