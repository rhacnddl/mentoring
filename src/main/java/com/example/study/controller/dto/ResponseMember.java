package com.example.study.controller.dto;

import com.example.study.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseMember {

    private String name;
    private Integer age;

    public ResponseMember(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static ResponseMember of(Member member) {
        return new ResponseMember(member.getName(), member.getAge());

    }
}
