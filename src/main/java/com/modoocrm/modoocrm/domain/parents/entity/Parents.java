package com.modoocrm.modoocrm.domain.parents.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@ToString
@Entity
@Getter
@Setter
@Table(name = "PARENTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parents_id")
    private Long parentsId;

    @Column(length = 10)
    private String fatherName;

    @Column(length = 10)
    private String motherName;

    @Column(length = 5)
    private int fatherAge;

    @Column(length = 5)
    private int motherAge;

    @Column(length = 10)
    private String fatherEducationInfo;

    @Column(length = 10)
    private String motherEducationInfo;

    @Column(length = 20)
    private String fatherJob;

    @Column(length = 20)
    private String motherJob;

    //동거 여부
    @Column(nullable = false, length = 20)
    private String together;

    @Setter
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Builder
    public Parents(String fatherName, String motherName, int fatherAge, int motherAge,
                   String fatherEducationInfo, String motherEducationInfo, String fatherJob, String motherJob, String together){
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.fatherAge = fatherAge;
        this.motherAge = motherAge;
        this.fatherEducationInfo = fatherEducationInfo;
        this.motherEducationInfo = motherEducationInfo;
        this.fatherJob = fatherJob;
        this.motherJob = motherJob;
        this.together = together;
    }

}
