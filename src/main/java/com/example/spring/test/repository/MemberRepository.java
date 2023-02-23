package com.example.spring.test.repository;

import com.example.spring.test.domain.Board;
import com.example.spring.test.domain.Member;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//@Configuration
public interface MemberRepository { //extends JpaRepository<Long, Member> {

    Optional<Member> findByLoginId(String id);
    Optional<Member> findMemberInfoById(String id);
    Member save(Member member); //회원 정보 저장소에 저장
    Optional<Member> findByIndex(Long index);
    Optional<Member> findByName(String name);
    Optional<Member> findById(String id);
    List<Member> findAll();
}
