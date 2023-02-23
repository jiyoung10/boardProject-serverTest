package com.example.spring.test.controller.dto;

import com.example.spring.test.domain.Board;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;

@Data
public class BoardContentRequest {

    private long index;
    private String updaterId;
    @NotNull
    @Column(length = 200)
    private String title;
    @NotNull
    @Column(length = 800)
    private String content;

    public BoardContentRequest(Board board) {
        this.index = index;
        this.updaterId =
        this.title = title;
        this.content = content;
    }
}
