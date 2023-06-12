package com.codestates.todoapp.member.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String username;

    public MemberResponseDto(String username) {
        this.username = username;
    }
}
