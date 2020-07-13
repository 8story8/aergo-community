package io.blocko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
