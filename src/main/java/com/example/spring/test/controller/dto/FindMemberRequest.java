package com.example.spring.test.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class FindMemberRequest {

    @NotNull
    private String name;
    private String id;
    private String pw;
    @NotNull
    private String phoneNumber;
}
