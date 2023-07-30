package com.modoocrm.modoocrm.domain.job.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "JOBS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(nullable = false)
    private String jobGroup;

    @Builder
    public Job(String jobGroup){
        this.jobGroup = jobGroup;
    }
}
