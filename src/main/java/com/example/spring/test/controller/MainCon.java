package com.example.spring.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainCon {

    @GetMapping("/")
    public String main(){
        return "main";
    }

}
