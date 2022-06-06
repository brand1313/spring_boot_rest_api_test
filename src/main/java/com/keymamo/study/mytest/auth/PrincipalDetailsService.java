package com.keymamo.study.mytest.auth;

import com.keymamo.study.mytest.domain.member.Member;
import com.keymamo.study.mytest.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오면 자동으로 PrincipalDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // requestBody에서 넘어오는 username의 키값과 파라미터인 username의 이름이 같아야 한다.
    // 만약 Post로 넘어오는 키값이 username2라면 매칭이 되지 않아 올바르게 동작하지 않는다.
    // 원하는 이름으로 변경하고 싶다면 securityconfig에서 usernameParameter를 사용한다.
    // 될 수 있으면 변경하지 말아라.

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByUsername(username);

        if (memberEntity != null) {
            return new PrincipalDetails(memberEntity);
        }

        return null;
    }
}
