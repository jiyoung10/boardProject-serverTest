package com.example.spring.test.boardRepository;

import com.example.spring.test.controller.dto.BoardContentRequest;
import com.example.spring.test.domain.Board;
import com.example.spring.test.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.SequenceInputStream;
import java.time.LocalDate;

@Slf4j
@SpringBootTest
public class boardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void contentSaveTest(){

        //검증해야 할 메소드에 getAttribute()를 이용해서 세셔을 가져오는 코드가 들어가는 경우,
        //JUnit으로 이 메소드를 테스트하면 세션에 null이 들어가기 때문에 error 발생
        //테스트할 경우 MockHttpSession을 사용하면 error 해결할 수 있음
        //HttpSession session = request.getSession();

        Board board = Board.builder()
                //.id(session.getAttribute("userId").toString())
                .title("test")
                .content("test")
                .createDate(LocalDate.now())
                .build();

        Board rs = boardRepository.saveBoardContent(board);
        log.info("saveBoardContent : " + rs);

    }

}
