package com.example.spring.test.repository;


import com.example.spring.test.domain.Member;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class MemoryMemberRepository implements MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //key값 생성

    @Override
    public Optional<Member> findByLoginId(String id) {
        return findAll().stream()
                        .filter(m -> m.getId().equals(id))
                        .findFirst();
    }

    @Override
    public Optional<Member> findMemberInfoById(String id) {
        return findAll().stream()
                        .filter(m->m.getId().equals(id))
                        .findFirst();
    }

    @Override
    public Member save(Member member) {
        member.setIndex(++sequence);
        member.setId(member.getId());
        member.setPw(member.getPw());
        member.setName(member.getName());
        member.setPhoneNumber(member.getPhoneNumber());
        store.put(member.getIndex(), member);
        return member;
    }

    @Override
    public Optional<Member> findByIndex(Long index) {
        return Optional.ofNullable(store.get(index));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.empty();
    }

    public List<Member> findAll() {
        return new ArrayList<>();
    }

    public void clearStore(){
        store.clear();
    }
}
