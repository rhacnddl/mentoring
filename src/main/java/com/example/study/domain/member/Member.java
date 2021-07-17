package com.example.study.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;


}
