package com.example.study.repository;

import com.example.study.domain.Board;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback(false)
class BoardRepositoryImplTest {

    @Autowired BoardRepository repository;

    @DisplayName("Read Board")
    @Test
    void findById() {
        //given
        Board sample = Board.builder().content("Just sample").build();
        Long id = repository.save(sample);

        //when
        Optional<Board> find = repository.findById(id);
        Board board = find.get();

        //then
        assertThat(board.getId()).isEqualTo(id);
    }

    @DisplayName("Save Board")
    @Test
    void save() {

        //given
        Board board = Board.builder()
                .content("No Annotations")
                .writer("admin")
                .regDate(LocalDateTime.now())
                .build();
        //when
        Long id = repository.save(board);

        //then
        assertThat(id).isEqualTo(board.getId());
    }

    @DisplayName("Delete Board")
    @Test
    void delete() {

        //given
        Board sample = Board.builder().content("Just sample").build();
        Long boardId = repository.save(sample);

        //when
        Long resultId = repository.delete(sample);

        Optional<Board> find = repository.findById(resultId);

        //then
        assertThat(find.isEmpty()).isEqualTo(true);
    }
}