package com.example.ollethboardproject.controller.request.chat;

import com.example.ollethboardproject.controller.Status;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}