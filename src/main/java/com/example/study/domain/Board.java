package com.example.study.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "BOARD")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private Long id;

    private String writer;

    private String content;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

}