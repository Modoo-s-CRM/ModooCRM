package com.modoocrm.modoocrm.domain.notice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Entity
@Getter
@Table(name = "NOTICE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noticeId;

    @Setter
    @Column(nullable = false)
    private LocalDateTime noticeDate;

    @Setter
    @Column
    private String counselSpecial;

    @Setter
    private LocalDate safeNoticeDate;

    @Setter
    private LocalDate deleteDate;

    @Builder
    public Notice(LocalDateTime noticeDate, String counselSpecial){
        this.noticeDate = noticeDate;
        this.counselSpecial = counselSpecial;
    }
}
