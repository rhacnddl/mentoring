package com.example.study.repository;

import com.example.study.domain.member.Member;
import com.example.study.domain.member.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
}
