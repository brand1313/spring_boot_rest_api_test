package com.keymamo.study.mytest.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Optional이 뭘까?
// CRUD 함수를 JpaRepository가 들고 있음.
// Repository 어노테이션이 없어도 IoC가 된다. JpaRepository를 상속했기 때문에..
public interface MemberRepository extends JpaRepository<Member, Integer> {

    // findBy 규칙 -> Username 문법 => JPA Query Method
    // 아래와 같은 쿼리가 된다.
    // SELECT * FROM user WHERE username = ?
    Member findByUsername(String username);

    // findByEmail 이면 아래와 같이 된다.
    // SELECT * FROM user WHERE Email = ?
}
