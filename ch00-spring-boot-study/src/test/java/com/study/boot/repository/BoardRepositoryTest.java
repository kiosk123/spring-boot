package com.study.boot.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.study.boot.domain.Board;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    void save() {
        Board board = new Board("게시물 제목", "게시물 작성자", "게시물 내용");
        boardRepository.save(board);
        em.flush();
        em.clear();
        
        Optional<Board> resultOpt = boardRepository.findById(board.getBno());
        
        if (resultOpt.isEmpty()) {
            fail("board is empty! check db connection or jpa setting!");
        }
        
        Board findBoard = resultOpt.get();

        assertThat(findBoard).isNotNull();
        assertThat(findBoard.getBno()).isEqualTo(board.getBno());
        assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(board.getContent());
    }
}
