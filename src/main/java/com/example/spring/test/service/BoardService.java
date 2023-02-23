package com.example.spring.test.service;

import com.example.spring.test.domain.Board;
import com.example.spring.test.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String contentRegister(String updaterId, String title, String content) {
        Board board = Board.builder()
                           .id(updaterId)
                           .title(title)
                           .content(content)
                           .createDate(LocalDate.now())
                           .build();

        Board rs = boardRepository.saveBoardContent(board);
        log.info("saveBoardContent : " + rs);
        return "saveContent success";
    }

    public List<Board> BoardList() {
        return boardRepository.findAll();
    }
}
