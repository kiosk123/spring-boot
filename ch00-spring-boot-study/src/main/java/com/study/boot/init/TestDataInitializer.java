package com.study.boot.init;

import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.study.boot.domain.Board;
import com.study.boot.domain.BoardReply;
import com.study.boot.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Profile("dev")
@Component
public class TestDataInitializer {
    
    @Autowired
    private TestDataSetter testDataSetter;
    
    @PostConstruct
    void init() {
        testDataSetter.insertData();
    }

}

@Profile("dev")
@Component
@RequiredArgsConstructor
class TestDataSetter {
    
    private final BoardRepository boardRepository;
    
    @Transactional
    void insertData() {
        IntStream.range(0, 200).forEach(n -> {
            Board board = new Board("제목 : " + n, "user0" + (n % 10), "게시물 내용 ... " + n + " 내용 채우기");
            IntStream.range(0, 3).forEach(k -> {
                BoardReply boardReply = new BoardReply("댓글" + k, "user" + k);
                board.addReply(boardReply); 
            });
            boardRepository.save(board);
        });
    }
}