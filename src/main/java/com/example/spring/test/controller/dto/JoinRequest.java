package com.example.spring.test.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class JoinRequest {

    @NotNull
    private String id;
    @NotNull
    private String pw;

    private String name;

    private String phoneNumber;

}
