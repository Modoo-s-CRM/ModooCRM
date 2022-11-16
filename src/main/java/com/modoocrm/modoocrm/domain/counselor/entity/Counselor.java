package com.modoocrm.modoocrm.domain.counselor.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Entity
@Table(name = "COUNSELOR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselor_id")
    private Long counselorId;

    @Column(nullable = false)
    private String counselorName;

    @Column(nullable = false)
    private Boolean counselorGender;

    @Builder
    public Counselor(String counselorName, Boolean counselorGender){
        this.counselorName = counselorName;
        this.counselorGender = counselorGender;
    }

}
