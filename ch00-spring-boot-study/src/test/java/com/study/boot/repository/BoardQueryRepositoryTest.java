package com.study.boot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.study.boot.repository.querydsl.BoardQueryRepository;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
class BoardQueryRepositoryTest {

    @Autowired
    BoardQueryRepository boardQeuryRepository;
    
    @Test
    void searchBoards() {
        Pageable pageable = PageRequest.of(1, 20);
        Page<Tuple> page = boardQeuryRepository.searchBoards("w", "user", pageable);
        
        assertEquals(20, page.getSize());
        assertEquals(1, page.getNumber());
        assertEquals(10, page.getTotalPages());
        assertEquals(200, page.getTotalElements());
    }

}
