package com.modoocrm.modoocrm.domain.client.entity;

import com.modoocrm.modoocrm.domain.base.BaseModel;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselDiary;
import com.modoocrm.modoocrm.domain.counselimage.entity.CounselImage;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//무분별한 객체 생성에 대해 한번 더 체크
public class Client extends BaseModel {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    @Column(nullable = false, length = 10)
    private String clientName;

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

    @OneToOne
    @JoinColumn(name = "counsel_image_id")
    private CounselImage counselImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_diary_id")
    private CounselDiary counselDiary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Parents parents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    @Builder
    public Client(String clientName, String birth, int age,
                  boolean clientGender, String address, String phone,
                  String hobby, String height, String weight,
                  String education, String marry, String job,
                  String counselType, String counselMethod,
                  String inflowPath, String symptom,
                  String counselHistory, String counselProgress,
                  String specialNote,Parents parents, Counselor counselor){
        this.clientName = clientName;
        this.birth = birth;
        this.age = age;
        this.clientGender = clientGender;
        this.address = address;
        this.phone = phone;
        this.hobby = hobby;
        this.height = height;
        this.weight = weight;
        this.education = education;
        this.marry = marry;
        this.job = job;
        this.counselType = counselType;
        this.counselMethod = counselMethod;
        this.inflowPath = inflowPath;
        this.symptom = symptom;
        this.counselHistory = counselHistory;
        this.counselProgress = counselProgress;
        this.specialNote = specialNote;
        this.parents = parents;
        this.counselor = counselor;
    }

    public void setClientId(Long clientId){
        this.clientId = clientId;
    }

}
