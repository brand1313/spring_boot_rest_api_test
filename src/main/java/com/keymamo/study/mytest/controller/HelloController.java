package com.keymamo.study.mytest.controller;

import com.keymamo.study.mytest.domain.member.Member;
import com.keymamo.study.mytest.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HelloController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String hello() {
        return "hello";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    //spring security가 낚아챔
    @GetMapping("/loginForm")
    public String loginForm() {
        return "login 하세요";
    }


    @PostMapping("/join")
    public void join(@RequestBody Member member, HttpServletResponse response) throws IOException {

        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //암호화

        member.setRole("ROLE_USER");
        member.setPassword(encPassword);

        memberRepository.save(member);

        response.sendRedirect("/loginForm");
    }
//
//    @GetMapping("/joinProc")
//    public String joinProc() {
//        return "회원가입 완료";
//    }

    // security config에 @EnableGlobalMethodSecurity(securedEnabled = true) 설정을 했기 때문에
    // 간단하게 api에 대한 권한을 부여할 수 있다.
    // admin만 접근할 수 있다.
    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public String info() {
        return "개인정보";
    }

    //security config에 @EnableGlobalMethodSecurity(prePostEnabled = true) 설정을 했기 때문에
    // 여러개 권한에 대해 api 접근을 정의할 수 있다. 하나의 권한만 정의하려면 @Secured 어노테이션을 사용하면 된다.
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public String data() {
        return "데이터 정보";
    }
}
