package com.codestates.todoapp.member.controller;

import com.codestates.todoapp.member.dto.MemberSignUpRequestDto;
import com.codestates.todoapp.member.entity.Member;
import com.codestates.todoapp.member.mapper.MemberMapper;
import com.codestates.todoapp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody MemberSignUpRequestDto requestDto){
        Member mapperMember = memberMapper.memberSignUpRequestDtoToMember(requestDto);
        Member signUpMember = memberService.signup(mapperMember);
        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(signUpMember), HttpStatus.CREATED);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive Long memberId){
        Member findMember = memberService.getMember(memberId);
        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(findMember),HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }



}
