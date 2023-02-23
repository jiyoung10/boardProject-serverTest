package com.example.spring.test.service;

import com.example.spring.test.domain.Member;
import com.example.spring.test.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service
//@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public String join(String id, String pw, String name, String phoneNumber) {
        Member member = Member.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();

        memberRepository.save(member);
        return "join success";
    }

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

//    public Member login(String id, String pw) {
//        return memberRepository.findByLoginId(id)
//                .filter(m -> m.getPw().equals(pw))
//                .orElse(null);
//    }

    public Member login(String id, String pw) {
        return memberRepository.findByLoginId(id)
                         .filter(m -> m.getPw().equals(pw))
                         .orElse(null);
    }


    public Member findId(String name, String phoneNumber) {
        return memberRepository.findByName(name)
                .filter(m -> m.getPhoneNumber().equals(phoneNumber))
                .orElse(null);
    }

    public Member findPw(String id, String phoneNumber) {
        return memberRepository.findById(id)
                .filter(m -> m.getPhoneNumber().equals(phoneNumber))
                .orElse(null);
    }

    public Member findMemberInfo(String id) {
        return memberRepository.findMemberInfoById(id)
                               .orElse(null);


    }

    public Member CheckIdDuplicate(String id) {
       return memberRepository.findById(id).orElse(null);
    }
}
