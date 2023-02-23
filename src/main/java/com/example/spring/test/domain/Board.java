package com.example.spring.test.domain;

import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.LocalDate.now;

@ToString
@Table(name = "Board")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Board")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter
    private Long index;
    @Setter
    private String title;
    @Setter
    private String content;

    @Setter
    private String id;

    @Setter
    private LocalDate createDate = LocalDate.now();

    public Board(Long index, String title, String content, String id) {
        this.index = index;
        this.title = title;
        this.content = content;
        this.id = id;
    }
}
