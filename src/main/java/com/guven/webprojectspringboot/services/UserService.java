package com.guven.webprojectspringboot.services;

import com.guven.webprojectspringboot.entities.Comment;
import com.guven.webprojectspringboot.entities.Like;
import com.guven.webprojectspringboot.entities.User;
import com.guven.webprojectspringboot.repos.CommentRepository;
import com.guven.webprojectspringboot.repos.LikeRepository;
import com.guven.webprojectspringboot.repos.PostRepository;
import com.guven.webprojectspringboot.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User user) {
        return userRepository.save(user);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User user) {
        Optional<User> dbUser = userRepository.findById(userId);
        if (dbUser.isPresent()) {
            User foundUser = dbUser.get();
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            foundUser.setAvatar(user.getAvatar());
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

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if (postIds.isEmpty())
            return null;
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;
    }
}
