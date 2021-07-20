package com.example.study.service;

import com.example.study.controller.dto.JoinMember;
import com.example.study.controller.dto.ResponseMember;
import com.example.study.domain.member.Member;
import com.example.study.domain.member.Role;
import com.example.study.domain.member.UserRole;
import com.example.study.repository.MemberRepository;
import com.example.study.repository.RoleRepository;
import com.example.study.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void join(JoinMember joinMember) {
        Member member = memberRepository.save(joinMember.toEntity());
        Role role = roleRepository.findByName(joinMember.getRoleName())
                .orElseThrow(() -> new RuntimeException());

        UserRole userRole = new UserRole(role, member);
        userRoleRepository.save(userRole);
    }

    @Transactional
    public void addRole(@RequestParam String roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
    }

    public List<String> getRoleAll() {
        List<Role> list = roleRepository.findAll();

        //if 로직

        //if 로직

        return list.stream()
                .map(l -> l.getName())
                .collect(Collectors.toList());
    }

    public ResponseMember get(String name) {
        Member member = memberRepository.findByTestName(name)
                .orElseThrow(() -> new RuntimeException());

        return ResponseMember.of(member);
    }

    public List<ResponseMember> getAll() {
        List<Member> list = memberRepository.findAll();
        return list.stream()
                .map(l -> new ResponseMember(l.getName(), l.getAge()))
                .collect(Collectors.toList());
    }
}
