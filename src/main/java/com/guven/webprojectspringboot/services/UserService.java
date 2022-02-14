package com.guven.webprojectspringboot.services;

import com.guven.webprojectspringboot.entities.User;
import com.guven.webprojectspringboot.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User user) {
        return userRepository.save(user);
    }

    public User getOneUser(Long userId) {
        return  userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User user) {
        Optional<User> dbUser = userRepository.findById(userId);
        if (dbUser.isPresent()) {
            User foundUser = dbUser.get();
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
