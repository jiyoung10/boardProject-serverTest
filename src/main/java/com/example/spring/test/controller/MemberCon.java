package com.example.spring.test.controller;

import com.example.spring.test.controller.dto.FindMemberInfoRequest;
import com.example.spring.test.controller.dto.FindMemberRequest;
import com.example.spring.test.controller.dto.JoinRequest;
import com.example.spring.test.controller.dto.LoginRequest;
import com.example.spring.test.domain.Member;
import com.example.spring.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberCon {

    private final MemberService memberService;

//    @Autowired
//    public MemberCon(MemberService memberService){
//        this.memberService = memberService;
//        System.out.println("memberService :" + memberService.getClass());
//    }

    //회원가입 폼 이동 메서드
    @GetMapping("/join.do")
    public String createForm(){
        return "/member/joinForm";
    }

    //회원가입 폼 실행 메서드
    @PostMapping("/join.do")
    public String create(@Valid JoinRequest joinRequest) {

        String id = joinRequest.getId();
        String pw = joinRequest.getPw();
        String name = joinRequest.getName();
        String phoneNumber = joinRequest.getPhoneNumber();

        //아이디 중복 체크
        String idCheck = String.valueOf(memberService.CheckIdDuplicate(id));

        if (idCheck == null){
            //중복되는 아이디 없을시 멤버 서비스의 join 메서드 실행
            String result = memberService.join(id, pw, name, phoneNumber);
        } else {
            //중복되는 아이디 존재시 에러페이지로 이동
            return "/member/joinError";
        }
        //join 메서드 정상 실행 후 다시 메인 페이지로 이동
        return "redirect:/";
    }

    //로그인 페이지 이동 메서드
    @GetMapping("/login.do")
    public String loginForm(){
        return "/member/loginForm";
    }

//    @PostMapping("/login.do")
//    public String login(Model model){
//        List<Member> members = memberService.findMember();
//        model.addAttribute("members", members);
//        return "loginAfter";
//    }

    //로그인 실행 메서드
    @PostMapping("/login.do")
    public String login(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest, BindingResult bindingResult,
                        HttpServletRequest request){
        Member loginMember = memberService.login(loginRequest.getId(), loginRequest.getPw());
        log.info("/member/loginMember : " + loginMember);

        //로그인 정보 비일치 시 rollback 처리
        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/member/loginForm";
        }

        //로그인 정보 Session 저장
        //게시글 작성자(id) 정보 및 내정보 키값(id)으로 활용
        HttpSession session = request.getSession();
        session.setAttribute("userId", loginRequest.getId());

        //로그인 후 페이지로 이동
        return "/member/loginAfter";
    }

    //아이디 찾기 페이지 이동 메서드
    @GetMapping("/findId.do")
    public String findIdForm(){
        return "/member/findMemberIdForm";
    }

    //아이디 찾기 실행 메서드
    @PostMapping("/findId.do")
    public String findId(@ModelAttribute("findMemberRequest") FindMemberRequest findMemberRequest, BindingResult bindingResult){
        Member findIdMembers = memberService.findId(findMemberRequest.getName(),findMemberRequest.getPhoneNumber());
        log.info("findIdMembers : " + findIdMembers);

        findMemberRequest.setId(findIdMembers.getId());

        //일치하는 정보 없을 시 rollback 처리
        if (findIdMembers == null){
            bindingResult.reject("findIdFail", "존재하는 계정이 없습니다.");
            return "/member/findMemberIdForm";
        }

        //아이디 찾은 후 페이지로 이동
        return "/member/findMemberIdAfter";
    }

    //비밀번호 찾기 페이지 이동
    @GetMapping("/findPw.do")
    public String findPwForm(){
        return "/member/findMemberPwForm";
    }

    //비밀번호 찾기 실행 메서드
    @PostMapping("/findPw.do")
    public String findPw(@ModelAttribute("findPwRequest") FindMemberRequest findMemberRequest, BindingResult bindingResult){
        Member findPwMembers = memberService.findPw(findMemberRequest.getId(), findMemberRequest.getPhoneNumber());
        log.info("findPwMembers : " + findPwMembers);

        findMemberRequest.setPw(findPwMembers.getPw());

        //일치하는 정보 없을 시 rollback 처리
        if (findPwMembers == null){
            bindingResult.reject("findPwFail", "존재하는 계정이 없습니다.");
            return "/member/findMemberPwForm";
        }

        //비밀번호 찾은 후 페이지로 이동
        return "/member/findMemberPwAfter";
    }

//    @GetMapping("/findMemberInfo.do")
//    public String findMemberInfoForm(){
//        return "/member/findMemberInFo";
//    }

//    @PostMapping("/findMemberInfo.do")
//    public String findMemberInfo(Model model, FindMemberInfoRequest findMemberInfoRequest, HttpServletRequest request) {
//
//        //Session을 불러와서 기존 login시 저장한 정보값들을 회원 정보에 세팅
//        HttpSession session = request.getSession();
//
//        findMemberInfoRequest.setId(session.getAttribute("userId").toString());
//        findMemberInfoRequest.setName(session.getAttribute("userName").toString());
//        findMemberInfoRequest.setPhoneNumber(session.getAttribute("phoneNumber").toString());
//
//        FindMemberInfoRequest infoList = findMemberInfoRequest;
//        log.info("info : " + infoList.toString());
//
//        model.addAttribute("info", infoList);
//
//
//        return "/member/findMemberInfo";
//    }

    //내정보 페이지 이동/실행 메서드
  //@PostMapping("/findMemberInfo.do")
    @RequestMapping(value = "/findMemberInfo.do", method = RequestMethod.GET)
    public String findMemberInfo(Model model, HttpServletRequest request) {

        //Session을 불러와서 기존 login시 저장한 id값을 키값으로 회원 정보 get
        HttpSession session = request.getSession();
        String id = session.getAttribute("userId").toString();
        log.info("id : " + id);

        //상기의 id값으로 멤버 서비스의 findMemberInfo 메서드를 실행하여 일치하는 정보 저장
        Member findMemberInfo = memberService.findMemberInfo(id);
        log.info("findMemberInfo : " + findMemberInfo);

        //저장된 정보를 info라는 객체로 만들어 내정보 페이지(view)에 전달
        model.addAttribute("info", findMemberInfo);

        //내정보 페이지로 이동
        return "/member/findMemberInfo";
    }

    //로그아웃 시 session 종료
    @RequestMapping(value = "/sessionDelete", method = RequestMethod.GET)
    public String sessionDelete(SessionStatus sessionStatus, HttpSession session){

        log.info("session userId : " + session.getAttribute("userId"));

        if (sessionStatus.isComplete() == false){
            sessionStatus.setComplete();
            log.info("session close");
        }
        //세션 종료 후 메인 페이지로 이동
        return "main";
    }

}
