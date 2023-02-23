package com.example.spring.test.repository;

import com.example.spring.test.domain.Board;

import java.util.List;

public interface BoardRepository {

    Board saveBoardContent(Board board);

    List<Board> findAll();
}
