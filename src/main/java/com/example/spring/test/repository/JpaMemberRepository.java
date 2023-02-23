package com.example.spring.test.repository;

import com.example.spring.test.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Member> findByLoginId(String id) {
        return findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }
    @Override
    public Optional<Member> findMemberInfoById(String id) {
        return findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByIndex(Long index) {
        Member member = em.find(Member.class, index);
        return Optional.ofNullable(member);
    }

//    @Override
//    public Optional<Member> findByName(String name) {
//        List<Member> reuslt = em.createQuery("select m from member m where name=:name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//
//        return reuslt.stream().findAny();
//    }

    @Override
    public Optional<Member> findByName(String name) {
        return findAll().stream()
                .filter(m -> m.getName().equals(name))
                .findFirst();
    }

//    @Override
//    public Optional<Member> findById(String id) {
//        List<Member> result = em.createQuery("select m from member m where id=:id", Member.class)
//                .setParameter("id", id)
//                .getResultList();
//
//        return result.stream().findAny();
//    }

    @Override
    public Optional<Member> findById(String id) {
        return findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

}
