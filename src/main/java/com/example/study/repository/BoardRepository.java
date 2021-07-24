package com.example.study.repository;

import com.example.study.domain.Board;

import java.util.Optional;

public interface BoardRepository {

    Optional<Board> findById(Long id);

    Long save(Board board);

    Long delete(Board board);
}
