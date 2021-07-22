package com.example.study.repository;

import com.example.study.domain.Board;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    @PersistenceContext
    private EntityManager em;

    public Optional<Board> findById(Long id){ return Optional.ofNullable(em.find(Board.class, id)); }

    @Override
    //@Transactional
    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    @Override
    public Long delete(Board board) {
        em.remove(board);
        return board.getId();
    }
}