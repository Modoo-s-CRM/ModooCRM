package com.modoocrm.modoocrm.api.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class ClientSearchFilterRepDto {

    List<ClientSearchFilterInfo> data;

    @Getter @Setter
    public static class ClientSearchFilterInfo {

        private Long clientId;
        private String clientName;
        private String clientGender;
        private int age;
        private String counselorName;
        private String phone;
        private String marry;
        private String job;
        private String counselType;
        private String counselProgress;
        private LocalDateTime createTime;

        @Builder
        public ClientSearchFilterInfo(Long clientId, String clientName, String clientGender, int age, String counselorName,
                                        String phone, String marry, String job, String counselType, String counselProgress, LocalDateTime createTime) {
            this.clientId = clientId;
            this.clientName = clientName;
            this.clientGender = clientGender;
            this.age = age;
            this.counselorName = counselorName;
            this.phone = phone;
            this.marry = marry;
            this.job = job;
            this.counselType = counselType;
            this.counselProgress = counselProgress;
            this.createTime = createTime;
        }
    }
}
