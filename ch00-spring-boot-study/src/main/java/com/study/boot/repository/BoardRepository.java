package com.study.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
