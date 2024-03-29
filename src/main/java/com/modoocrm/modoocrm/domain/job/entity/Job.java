package com.modoocrm.modoocrm.domain.job.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "job")
    private List<Client> clients = new ArrayList<>();

    @Builder
    public Job(String jobGroup){
        this.jobGroup = jobGroup;
    }


}
