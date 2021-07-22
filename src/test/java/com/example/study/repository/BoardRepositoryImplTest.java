package com.example.study.repository;

import com.example.study.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BoardRepositoryImplTest {

    @Autowired BoardRepository repository;

    @Test
    void findById() {
        //given
        Long id = 5L;

        //when
        Optional<Board> find = repository.findById(id);
        Board board = find.get();

        //then
        Assertions.assertThat(board.getId()).isEqualTo(id);
    }

    @Test
    //@Transactional
    //@Rollback(false)
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
        Assertions.assertThat(id).isEqualTo(board.getId());
    }

    @Test
    @Transactional
    @Rollback(value = false) //@Rollback(value = false)를 안해주면 이후 쿼리가 안나간다. 이유는?
    void delete() {

        //given
        Board target = repository.findById(5L).get();

        //when
        Long resultId = repository.delete(target);

        Optional<Board> find = repository.findById(resultId);

        //then
        Assertions.assertThat(find.isEmpty()).isEqualTo(true);
    }
}