package com.guven.webprojectspringboot.repos;

import com.guven.webprojectspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
