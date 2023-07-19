package com.modoocrm.modoocrm.domain.client.entity;

import com.modoocrm.modoocrm.domain.base.BaseModel;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselDiary;
import com.modoocrm.modoocrm.domain.counselimage.entity.CounselImage;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성에 대해 한번 더 체크
public class Client extends BaseModel {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Setter
    @Column(nullable = false, length = 10)
    private String clientName;

    @Setter
    @Column(nullable = false, length = 20 )
    private LocalDate birth;

    @Setter
    @Column(nullable = false)
    private int age;

    @Setter
    @Column(nullable = false)
    private String clientGender;

    @Setter
    @Column(nullable = false, length = 50)
    private String address;

    @Setter
    @Column(nullable = false, length = 15)
    private String phone;

    @Setter
    @Column(length = 50)
    private String hobby;

    @Setter
    @Column(length = 10)
    private String height;

    @Setter
    @Column(length = 10)
    private String weight;

    @Setter
    @Column(length = 10)
    private String educationInfo;

    @Setter
    @Column(nullable = false, length = 10)
    private String marry;

    @Setter
    @Column(nullable = false, length = 20)
    private String job;

    @Setter
    @Column(nullable = false, length = 20)
    private String counselType;

    @Setter
    @Column(nullable = false, length = 20)
    private String counselMethod;

    @Setter
    @Column(nullable = false, length = 20)
    private String inflowPath;

    @Setter
    @Column(nullable = false, length = 30)
    private String symptom;

    @Setter
    @Column(nullable = false, length = 20)
    private String counselHistory;

    @Setter
    @Column(nullable = false, length = 20)
    private String counselProgress;

    @Setter
    @Column(nullable = false)
    private LocalDateTime firstCounsel;

    @Setter
    @Column
    private String specialNote;

    @Setter
    @OneToOne(mappedBy = "client")
    private CounselImage counselImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_diary_id")
    private CounselDiary counselDiary;

    @OneToOne(mappedBy = "client")
    private Parents parents;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    @Builder
    public Client(String clientName, LocalDate birth, int age, String clientGender, String address, String phone,
                  String hobby, String height, String weight, String educationInfo, String marry, String job,
                  String counselType, String counselMethod, String inflowPath, String symptom, String counselHistory,
                  String counselProgress, LocalDateTime firstCounsel, String specialNote){
        this.clientName = clientName;
        this.birth = birth;
        this.age = age;
        this.clientGender = clientGender;
        this.address = address;
        this.phone = phone;
        this.hobby = hobby;
        this.height = height;
        this.weight = weight;
        this.educationInfo = educationInfo;
        this.marry = marry;
        this.job = job;
        this.counselType = counselType;
        this.counselMethod = counselMethod;
        this.inflowPath = inflowPath;
        this.symptom = symptom;
        this.counselHistory = counselHistory;
        this.counselProgress = counselProgress;
        this.firstCounsel = firstCounsel;
        this.specialNote = specialNote;
    }
}
