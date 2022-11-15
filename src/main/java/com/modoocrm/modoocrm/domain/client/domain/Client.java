package com.modoocrm.modoocrm.domain.client.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Getter @Setter
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 10)
    private String clientName;

    @Column(nullable = false, length = 10)
    private String counselorName;

    @Column(nullable = false, length = 20 )
    private String birth;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private boolean clientGender;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = true, length = 50)
    private String hobby;

    @Column(nullable = true, length = 10)
    private String height;

    @Column(nullable = true, length = 10)
    private String weight;

    @Column(nullable = true, length = 10)
    private String education;

    @Column(nullable = false, length = 10)
    private String marry;

    @Column(nullable = false, length = 20)
    private String job;

    @Column(nullable = false, length = 20)
    private String counselType;

    @Column(nullable = false, length = 20)
    private String counselMethod;

    @Column(nullable = false, length = 20)
    private String inflowPath;

    @Column(nullable = false, length = 30)
    private String symptom;

    @Column(nullable = false, length = 20)
    private String counselHistory;

    @Column(nullable = false, length = 20)
    private String counselProgress;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    @Column(nullable = true)
    private String specialNote;




}
