package com.example.spring.test.memberRepository;

import com.example.spring.test.domain.Member;
import com.example.spring.test.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void crudTest(){
    Member member = Member.builder()
                          .id("java")
                          .pw("9090")
                          .name("김자바")
                          .phoneNumber("010-9090-9090")
                          .build();

//        HttpSession session = request.getSession();
//        session.setAttribute("userId", member.getId());

        //create test
        memberRepository.save(member);
        //get test
        //Member findMember = memberRepository.findById("java").get();
    }
}
