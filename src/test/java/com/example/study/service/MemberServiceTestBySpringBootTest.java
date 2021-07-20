package com.example.study.service;

import com.example.study.controller.dto.ResponseMember;
import com.example.study.domain.member.Role;
import com.example.study.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTestBySpringBootTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @DisplayName("SpringBoot Test")
    @Test
    void test() {
        //given
        memberService.addRole("테스트1");
        memberService.addRole("테스트2");
        //when
        List<String> list = memberService.getRoleAll();
        //then
        assertThat(list).containsExactly("테스트1", "테스트2");
    }
}
