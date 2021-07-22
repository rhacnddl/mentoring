package com.example.study.repository;

import com.example.study.domain.Board;
import com.example.study.domain.Reply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Reply> findById(Long id) {
        return Optional.ofNullable(em.find(Reply.class, id));
    }

    @Override
    public Long save(Reply reply) {
        em.persist(reply);
        return reply.getId();
    }

    @Override
    public Long delete(Reply reply) {
        em.remove(reply);
        return reply.getId();
    }

    @Override
    public Optional<List<Reply>> findAllByBoard(Board board) {
        return Optional.ofNullable(em.createQuery("SELECT R FROM Reply R WHERE R.board = :board", Reply.class)
                .setParameter("board", board)
                .getResultList());
    }
}