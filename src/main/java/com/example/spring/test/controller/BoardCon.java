package com.example.spring.test.controller;

import com.example.spring.test.controller.dto.BoardContentRequest;
import com.example.spring.test.domain.Board;
import com.example.spring.test.domain.Member;
import com.example.spring.test.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardCon {

    public final BoardService boardService;

//    @RequestMapping(value = "/boardPage", method = RequestMethod.GET)
//    public String getBoardPage(Model model){
//        List<Board> boardContents = boardService.BoardList();
//        log.info("boardContents : " + boardContents);
//
//        model.addAttribute("list", boardContents);
//
//        return "/board/boardPage";
//    }

    //게시글 작성 페이지 이동 메서드
    @GetMapping("/boardPage")
    public String getBoardPage(){
        return "/board/boardPage";
    }

    //게시글 작성 실행 메서드
    @PostMapping("/boardPage")
    public String saveBoardContent(BoardContentRequest boardContentRequest, HttpServletRequest request){

        HttpSession session = request.getSession();

        String title = boardContentRequest.getTitle();
        String content = boardContentRequest.getContent();
        //로그인 시 httpSession에 저장한 id값(userId)을 id로 세팅
        String updaterId = session.getAttribute("userId").toString();
        log.info("updaterId : " +  updaterId);
        //세팅된 id, title, content를 넘겨주어 보드 서비스의 contentRegister(저장) 메서드 실행
        boardService.contentRegister(updaterId, title, content);

        try{
            //게시글 저장 후 기존 게시글 작성 페이지로 이동
            return "board/boardPage";
        }catch (Exception e){
            //예외 발생 시 에러 페이지로 이동
            return "/errorPage";
        }
    }

//    @PostMapping("/boardContent.do")
//    public String baordContentAfter(@ModelAttribute BoardContentRequest boardContentRequest, BindingResult bindingResult){
//        Board boardContents = memberService.searchContent(boardContentRequest.getTitle(), boardContentRequest.getContent());
//        log.info("boardContents : " + boardContents);
//
//        if(boardContentRequest == null){
//            bindingResult.reject("contentSearchFail", "작성된 게시글이 존재하지 않습니다.");
//
//            return "boardPage";
//        }
//        return "boardContentSaved";
//    }

//    @GetMapping("/boardList")
//    public String getBoardList(){
//        return "boardList";
//    }

    //게시글 목록 이동/실행 메서드
    @RequestMapping(value="/boardList", method=RequestMethod.GET)
    public String getBoardList(Model model){
        //보드 서비스의 boardList(findAll) 실행 결과값을 list에 저장
        List<Board> boardContents = boardService.BoardList();
        log.info("boardContents : " + boardContents);

        //boardContents(list 저장값)을 list라는 객체로 생성
        model.addAttribute("list", boardContents);

        //list라는 객체를 게시글 목록 페이지로 전달
        return "/board/boardList";
    }


}
