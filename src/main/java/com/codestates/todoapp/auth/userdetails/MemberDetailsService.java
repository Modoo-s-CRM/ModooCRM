package com.codestates.todoapp.auth.userdetails;

import com.codestates.todoapp.auth.utils.CustomAuthorityUtils;
import com.codestates.todoapp.exception.BusinessLogicException;
import com.codestates.todoapp.exception.ExceptionCode;
import com.codestates.todoapp.member.entity.Member;
import com.codestates.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return new MemberDetailsImpl(findMember);
    }

    private final class MemberDetailsImpl extends Member implements UserDetails{

        private final Member member;

        MemberDetailsImpl(Member member) {
            this.member = member;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(member.getRoles());
        }

        public Member getMember(){
            return member;
        }

        @Override
        public String getUsername() {
            return member.getUsername();
        }

        @Override
        public String getPassword() {
            return member.getPassword();
        }


        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
