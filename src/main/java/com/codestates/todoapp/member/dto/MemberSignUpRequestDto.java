package com.codestates.todoapp.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String gender;

}
