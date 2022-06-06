package com.keymamo.study.mytest.domain.member;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private int id; // 기본키
    private String username; // 사용자 아이디
    private String password; // 사용자 비밀번호
    private String email; // 사용자 이메일
    private String role; // 사용자 권한

}

