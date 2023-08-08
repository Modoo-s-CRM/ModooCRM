package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class JobRepDto {

    private List<JobStats> data;

    public static class JobStats{
        private String job;
        private double ratio;

        @Builder
        public JobStats(String job, double ratio) {
            this.job = job;
            this.ratio = ratio;
        }
    }
}
