package com.example.spring.test.repository;

import com.example.spring.test.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;

    @Autowired
    public JpaBoardRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public Board saveBoardContent(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }
}
