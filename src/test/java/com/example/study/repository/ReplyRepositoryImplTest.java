package com.example.study.repository;

import com.example.study.domain.Board;
import com.example.study.domain.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Rollback(value = false)
@Transactional
class ReplyRepositoryImplTest {

    @Autowired ReplyRepository repository;
    @Autowired BoardRepository boardRepository;

    @Test
    @DisplayName("댓글 조회")
    void findById() {

        //given
        Board sampleBoard = Board.builder().content("Sample Board").build();
        Long boardId = boardRepository.save(sampleBoard);

        Reply sample = Reply.builder().board(sampleBoard).content("Sample Reply").build();
        Long replyId = repository.save(sample);

        //when
        Optional<Reply> op = repository.findById(replyId);
        Reply reply = op.get();

        //then
        assertEquals(replyId, reply.getId());
    }

    @Test
    @DisplayName("댓글 저장")
    void save() {

        //given
        //조회 -> 1차 캐시에 board 저장
        Board sampleBoard = Board.builder().content("Sample Board").build();
        Long boardId = boardRepository.save(sampleBoard);

        //when
        IntStream.range(0, 10).forEach(i ->
            //저장 -> 1차 캐시에 reply 저장 / 쓰기지연 SQL 저장소에 쌓임
            //    -> 만약 MYSQL dialect이고 @GeneratedValue(IDENTITY)를 주었다면, 각 save는 INSERT 쿼리를 날렸을 것
            //    -> INSERT 쿼리를 날려야 AUTO_INCREMENT값을 알 수 있으므로
            repository.save(
                    Reply.builder()
                            .content(i + "번째 댓글")
                            .regDate(LocalDateTime.now())
                            .board(sampleBoard)
                            .build()
            )
        );

        //then
        //JPQL을 만났으므로 EntityManager가 flush() -> 쓰기지연 SQL 저장소의 SQL 모두 실행
        //10번의 INSERT
        List<Reply> replies = repository.findAllByBoard(sampleBoard).get();
        assertEquals(replies.size(), 10);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() {

        //given
        Reply sample = Reply.builder().content("Sample Reply").build();
        Long reply = repository.save(sample);

        //when
        Long replyId = repository.delete(sample);

        //then
        Optional<Reply> find = repository.findById(replyId);
        assertEquals(find.isEmpty(), true);
    }

}