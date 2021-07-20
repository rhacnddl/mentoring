package com.example.study.service;

import com.example.study.domain.member.Role;
import com.example.study.repository.MemberRepository;
import com.example.study.repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    MemberService memberService;

    @DisplayName("Mockito Test")
    @Test
    void test() {
        //given
        List<Role> role = new ArrayList<>();
        role.add(new Role("테스트1"));
        role.add(new Role("테스트2"));
        when(roleRepository.findAll()).thenReturn(role);
        //when
        List<String> list = memberService.getRoleAll();
        //then
        assertThat(list).containsExactly("테스트1", "테스트2");
    }

}