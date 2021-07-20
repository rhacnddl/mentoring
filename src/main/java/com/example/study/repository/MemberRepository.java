package com.example.study.repository;

import com.example.study.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.name = :name")
    Optional<Member> findByTestName(@Param("name") String name);
}
