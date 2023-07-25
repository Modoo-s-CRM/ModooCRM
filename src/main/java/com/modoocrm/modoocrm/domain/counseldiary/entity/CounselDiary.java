package com.modoocrm.modoocrm.domain.counseldiary.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private int counselCount;

    @Column(nullable = false)
    private LocalDateTime counselDate; //당일 상담 날짜

    @Column(nullable = false)
    private String clientCondition;

    @Column(nullable = false)
    private String counselPlan;

    @Column(nullable = false)
    private String counselResult;

    @Column
    private LocalDateTime nextCounselDate;

    @Column
    private String nextCounselPlan;

    @Column
    private String note; //비고

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

}
