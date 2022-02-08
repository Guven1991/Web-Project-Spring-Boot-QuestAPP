package com.guven.webprojectspringboot.repos;

import com.guven.webprojectspringboot.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
