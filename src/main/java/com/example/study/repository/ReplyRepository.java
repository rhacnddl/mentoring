package com.example.study.repository;

import com.example.study.domain.Reply;

import java.util.Optional;

public interface ReplyRepository {

    Optional<Reply> findById(Long id);

    Long save(Reply reply);

    Long delete(Reply reply);
}