package com.modoocrm.modoocrm.api.client.dto;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegisterReqDto {

    private String clientName;

    private String birth;

    private int age;

    private boolean clientGender;

    private String address;

    private String phone;

    private String hobby;

    private String height;

    private String weight;

    private String education;

    private String marry;

    private String job;

    private String counselType;

    private String counselMethod;

    private String inflowPath;

    private String symptom;

    private String counselHistory;

    private String counselProgress;

    private String specialNote;

//    public Parents tempParents(){
//        Parents parents = new Parents();
//        parents.setParentsId(1L);
//        parents.setParentsName("김정근");
//        parents.setAge(31);
//        parents.setEducation("대졸");
//        parents.setJob("개발자");
//        parents.setTogether("동거");
//        return parents;
//    }
//
//    public Counselor counselor(){
//        Counselor counselor = new Counselor();
//        counselor.setCounselorId(1L);
//        counselor.setCounselorName("안자미");
//        counselor.setCounselorGender(false);
//        return counselor;
//    }


    //엔티티화
    public Client toEntity(){
        return Client.builder()
                .clientName(clientName)
                .birth(birth)
                .age(age)
                .clientGender(clientGender)
                .address(address)
                .phone(phone)
                .hobby(hobby)
                .height(height)
                .weight(weight)
                .education(education)
                .marry(marry)
                .job(job)
                .counselType(counselType)
                .counselMethod(counselMethod)
                .inflowPath(inflowPath)
                .symptom(symptom)
                .counselHistory(counselHistory)
                .counselProgress(counselProgress)
                .specialNote(specialNote)
                .build();
    }

}
