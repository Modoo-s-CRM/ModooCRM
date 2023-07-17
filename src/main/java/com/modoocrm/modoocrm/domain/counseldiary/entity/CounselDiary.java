package com.modoocrm.modoocrm.domain.counseldiary.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Getter
@Table(name = "COUNSELDIARY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselDiary {

    @Id
    @Column(name = "counsel_diary_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diaryId;

    @Column(nullable = false)
    private Date counselDate;

    @Column(nullable = false, length = 30)
    private String counselTime;

    @Column(nullable = false)
    private String clientCondition;

    @Column(nullable = false)
    private String counselPlan;

    @Column(nullable = false)
    private String counselResult;

    @Column
    private Date nextDate;

    @Column(length = 30)
    private String nextTime;

    @Column
    private String nextCounsel;

    @Column
    private String specialNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

}
