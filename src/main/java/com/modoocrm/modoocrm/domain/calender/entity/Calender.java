package com.modoocrm.modoocrm.domain.calender.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@Entity
@Getter
@Table(name = "CALENDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calender {

    @Id
    @Column(name = "calender_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long calenderId;

    @Column(nullable = false)
    private Date calenderDate;

    @Column
    private String counselSpecial;

    @Column(nullable = false)
    private String watcher;

}
