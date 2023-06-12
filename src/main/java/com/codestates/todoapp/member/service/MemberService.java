package com.codestates.todoapp.member.service;

import com.codestates.todoapp.auth.utils.CustomAuthorityUtils;
import com.codestates.todoapp.exception.BusinessLogicException;
import com.codestates.todoapp.exception.ExceptionCode;
import com.codestates.todoapp.member.entity.Member;
import com.codestates.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils customAuthorityUtils;

    public Member signup(Member member){
        verifyMemberExist(member.getUsername());
        String password = passwordEncoder.encode(member.getPassword());
        member.setPassword(password);

        //DB에 User Role 저장
        List<String> roles = customAuthorityUtils.createRoles(member.getUsername());
        member.setRoles(roles);


        return memberRepository.save(member);
    }

    public Member getMember(Long memberId){
        Member findMember = verifyFindMember(memberId);
        return findMember;
    }

    public void deleteMember(Long memberId){
        Member member = verifyFindMember(memberId);
        memberRepository.delete(member);
    }



    public Member verifyFindMember(Long memberId){
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyMemberExist(String username){
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
        }
    }

    public Member verifyUsername(String username){
        Member findMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

}
