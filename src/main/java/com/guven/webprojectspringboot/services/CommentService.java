package com.guven.webprojectspringboot.services;

import com.guven.webprojectspringboot.entities.Comment;
import com.guven.webprojectspringboot.entities.Post;
import com.guven.webprojectspringboot.entities.User;
import com.guven.webprojectspringboot.repos.CommentRepository;
import com.guven.webprojectspringboot.requests.CommentCreateRequest;
import com.guven.webprojectspringboot.requests.CommentUpdateRequest;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService= userService;
        this.postService=postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }

    public Comment getOneComment(Long commentId) {

        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User dbUser = userService.getOneUser(commentCreateRequest.getUserId());
        Post dbPost =postService.getOnePost(commentCreateRequest.getPostId());
        if(dbUser != null && dbPost != null){
           Comment  commentToSave = new Comment();
           commentToSave.setId(commentCreateRequest.getId());
           commentToSave.setPost(dbPost);
           commentToSave.setUser(dbUser);
           commentToSave.setText(commentCreateRequest.getText());
           return commentRepository.save(commentToSave);
        }else
            return null;

    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> dbComment = commentRepository.findById(commentId);
        if(dbComment.isPresent()){
            Comment commentToUpdate = dbComment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);

        }else
            return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
