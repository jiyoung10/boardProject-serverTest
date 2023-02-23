package com.example.spring.test.controller.dto;

import lombok.Data;

@Data
public class FindMemberInfoRequest {

    private String id;
    private String name;
    private String phoneNumber;

}
