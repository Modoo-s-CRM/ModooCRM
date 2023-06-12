package com.codestates.todoapp.member.mapper;

import com.codestates.todoapp.member.dto.MemberResponseDto;
import com.codestates.todoapp.member.dto.MemberSignUpRequestDto;
import com.codestates.todoapp.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member memberSignUpRequestDtoToMember(MemberSignUpRequestDto memberSignUpRequestDto){
        return new Member(
                memberSignUpRequestDto.getUsername(),
                memberSignUpRequestDto.getPassword(),
                memberSignUpRequestDto.getGender()
                );
    }

    public MemberResponseDto memberToMemberResponseDto(Member member){
        return new MemberResponseDto(member.getUsername());
    }



}
