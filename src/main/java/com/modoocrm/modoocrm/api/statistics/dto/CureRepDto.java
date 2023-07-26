package com.modoocrm.modoocrm.api.statistics.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CureRepDto {

    private List<CureStats> data;

    @Getter @Setter
    public static class CureStats{
        private String month;
        private int cure;

        @Builder
        public CureStats(String month, int cure){
            this.month = month;
            this.cure = cure;
        }
    }
}
