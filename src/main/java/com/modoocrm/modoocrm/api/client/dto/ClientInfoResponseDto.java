package com.modoocrm.modoocrm.api.client.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ClientInfoResponseDto {

    private String clientName;
    private LocalDate birth;
    private int age;
    private String clientGender;
    private String address;
    private String phone;
    private String hobby;
    private String height;
    private String weight;
    private String educationInfo;
    private String marry;
    private String job;
    private String counselType;
    private String counselMethod;
    private String inflowPath;
    private String symptom;
    private String symptomGrade;
    private String counselHistory;
    private String counselProgress;
    private LocalDateTime firstCounsel;
    private String specialNote;

    private String counselorName;
    private String counselorGender;

    private String applicationFormImagePath;
    private String selfAptitudeImagePath;
    private String landscapeImagePath;

    @Builder
    public ClientInfoResponseDto(String clientName, LocalDate birth, int age, String clientGender, String address, String phone,
                                 String hobby, String height, String weight, String educationInfo, String marry, String job,
                                 String counselType, String counselMethod, String inflowPath, String symptom, String symptomGrade,
                                 String counselHistory, String counselProgress, LocalDateTime firstCounsel, String specialNote,
                                 String counselorName, String counselorGender,
                                 String applicationFormImagePath, String selfAptitudeImagePath, String landscapeImagePath){
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
        this.symptomGrade = symptomGrade;
        this.counselHistory = counselHistory;
        this.counselProgress = counselProgress;
        this.firstCounsel = firstCounsel;
        this.specialNote = specialNote;
        this.counselorName = counselorName;
        this.counselorGender = counselorGender;
        this.applicationFormImagePath = applicationFormImagePath;
        this.selfAptitudeImagePath = selfAptitudeImagePath;
        this.landscapeImagePath = landscapeImagePath;

    }
}
