package com.guven.webprojectspringboot.repos;

import com.guven.webprojectspringboot.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
