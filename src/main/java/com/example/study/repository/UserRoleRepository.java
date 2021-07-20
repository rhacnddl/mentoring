package com.example.study.repository;

import com.example.study.domain.member.Member;
import com.example.study.domain.member.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
