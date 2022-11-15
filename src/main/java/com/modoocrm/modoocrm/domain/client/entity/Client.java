package com.modoocrm.modoocrm.domain.client.entity;

import com.modoocrm.modoocrm.domain.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter @Setter
public class Client extends BaseModel {

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

    @Column(length = 50)
    private String hobby;

    @Column(length = 10)
    private String height;

    @Column(length = 10)
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

    @Column(nullable = true)
    private String specialNote;




}
