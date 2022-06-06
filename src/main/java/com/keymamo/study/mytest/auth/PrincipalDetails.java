package com.keymamo.study.mytest.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행 시킨다.
// 로그인 진행이 완료가 되면 스프링 시큐리티가 자신만의 session을 만든다.(보통세션과 key가 다르다.)
// key : Security ContextHolder

// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨.
// User 오브젝트 타입 => UserDetails 타입 객체

// 즉, 스프링 시큐리티가 가지고 있는 세션 영역(Security Session)에 들어갈 수 있는 객체가 정해져 있다.
// => Authentication (여기서는 PrincipalDetailsService)
// => UserDetails (Authentication 안에..)

import com.keymamo.study.mytest.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private Member member; //call 포지션

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    //해당 User의 권한을 리턴하는 곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
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

        //우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면계정으로 하기로 함.
        //현재시간 - 로그인시간 => 1년이 지났으면 false 리턴
        return true;
    }
}
