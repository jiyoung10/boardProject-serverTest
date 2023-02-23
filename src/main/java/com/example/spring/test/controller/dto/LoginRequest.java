package com.example.spring.test.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import org.thymeleaf.messageresolver.IMessageResolver;

@Data
public class LoginRequest {

    @NotNull
    private String id;
    @NotNull
    private String pw;

    private String name;

    private String phoneNumber;

}
