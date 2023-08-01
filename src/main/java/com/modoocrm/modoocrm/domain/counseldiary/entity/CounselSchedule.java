package com.modoocrm.modoocrm.domain.counseldiary.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COUNSELSCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselSchedule {

    @Id
    @Column(name = "counsel_schedule_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long counselScheduleId;

    @Setter
    @Column(nullable = false)
    private LocalDateTime theDayCounselDate; //당일 상담 일정

    @Setter
    @Column
    private int firstCounselCount; // 초진 상담 횟수

    @Setter
    @Column
    private int cureCounselCount; // 치료 상담 횟수

    @Setter
    @Column
    private LocalDateTime nextCounselDate; // 다음 상담 일정

    @Setter
    @Column
    private String note; //비고

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    @Builder
    public CounselSchedule(LocalDateTime theDayCounselDate, int firstCounselCount, int cureCounselCount,
                           LocalDateTime nextCounselDate, String note){
        this.theDayCounselDate = theDayCounselDate;
        this.firstCounselCount = firstCounselCount;
        this.cureCounselCount = cureCounselCount;
        this.nextCounselDate = nextCounselDate;
        this.note = note;
    }

    public void update(CounselSchedule counselSchedule){
        this.theDayCounselDate = counselSchedule.getTheDayCounselDate();
        this.firstCounselCount = counselSchedule.getFirstCounselCount();
        this.cureCounselCount = counselSchedule.getCureCounselCount();
        this.nextCounselDate = counselSchedule.getNextCounselDate();
        this.note = counselSchedule.getNote();
    }
}
