package com.modoocrm.modoocrm.domain.client.entity;

import com.modoocrm.modoocrm.domain.base.BaseModel;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselDiary;
import com.modoocrm.modoocrm.domain.counselimage.entity.CounselImage;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private CounselType counselType;

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
    private LocalDate endCounsel;

    @Setter
    @Column
    private String specialNote;

    //치료상담여부
    @Setter
    private Boolean isCure = false;

    @Setter
    @OneToOne(mappedBy = "client")
    private CounselImage counselImage;

    @OneToMany(mappedBy = "client")
    private List<CounselDiary> counselDiary;

    @OneToOne(mappedBy = "client")
    private Parents parents;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    public void addCouselorDiary(CounselDiary counselDiary){
        this.counselDiary.add(counselDiary);
        if (counselDiary.getClient() != this){
            counselDiary.setClient(this);
        }
    }

    public enum CounselType{
        ADULT("성인 상담"),
        MARRIED_COUPLE("부부 상담"),
        COUPLE("커플 상담"),
        FAMILY("가족 상담"),
        YOUTH("아동청소년 상담"),
        ANTENATAL("태교 상담"),
        GROUP("그룹 상담");

        @Getter
        private String counselTypeDescription;

        CounselType(String counselTypeDescription){
            this.counselTypeDescription = counselTypeDescription;
        }

        public static CounselType findByDescription(String description){
            for (CounselType counselType : values() ){
                if (counselType.getCounselTypeDescription().equals(description)){
                    return counselType;
                }
            }
            throw new BusinessLogicException(ExceptionCode.INVALID_COUNSEL_TYPE);
        }

    }

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
        this.counselType = CounselType.findByDescription(counselType);
        this.counselMethod = counselMethod;
        this.inflowPath = inflowPath;
        this.symptom = symptom;
        this.counselHistory = counselHistory;
        this.counselProgress = counselProgress;
        this.firstCounsel = firstCounsel;
        this.specialNote = specialNote;
    }
}
