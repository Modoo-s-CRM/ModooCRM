package com.modoocrm.modoocrm.api.client.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
public class ClientRegisterDto {

    @NotBlank(message = "내담자 성함은 필수 입력 값입니다.")
    private String clientName;

    @NotBlank(message = "상담사 성함은 필수 입력 값입니다.")
    private String counselorName;

    //1995-09-26 형식으로 들어올 예정 -> LocalDate로 변경 필요
    @NotBlank
    private String birth;

    @Positive
    @Range(min = 1, max = 100)
    private int age;

    @NotBlank(message = "성별은 필수 입력 값입니다.")
    private String clientGender;

    @NotBlank(message = "주소값은 필수 입력 값입니다.")
    private String address;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
    private String phone;

    private String hobby;

    private String height;

    private String weight;

    private String educationInfo;

    @NotBlank(message = "결혼유무는 필수 입력 값입니다.")
    private String marry;

    @NotBlank(message = "직업은 필수 입력 값입니다.")
    private String job;

    @NotBlank(message = "상담 유형은 필수 입력 값입니다.")
    private String counselType;

    @NotBlank(message = "상담 방법은 필수 입력 값입니다.")
    private String counselMethod;

    @NotBlank(message = "상담 유입 경로는 필수 입력 값입니다.")
    private String inflowPath;

    @NotBlank(message = "심리 증상은 필수 입력 값입니다.")
    private String symptom;

    @NotBlank(message = "상담 경력은 필수 입력 값입니다.")
    private String counselHistory;

    @NotBlank(message = "상담 진행 상황은 필수 입력 값입니다.")
    private String counselProgress;

    @NotBlank(message = "초진 상담 날짜는 필수 입력 값입니다.")
    private String firstCounsel;

    private String specialNote;

}
