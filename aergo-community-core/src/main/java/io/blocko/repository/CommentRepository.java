package io.blocko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.blocko.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
