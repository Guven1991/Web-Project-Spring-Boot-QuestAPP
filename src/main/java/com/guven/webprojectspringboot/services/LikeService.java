package com.guven.webprojectspringboot.services;

import com.guven.webprojectspringboot.entities.Like;
import com.guven.webprojectspringboot.entities.Post;
import com.guven.webprojectspringboot.entities.User;
import com.guven.webprojectspringboot.repos.LikeRepository;
import com.guven.webprojectspringboot.requests.LikeCreateRequest;
import com.guven.webprojectspringboot.response.LikeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private PostService postService;
    private UserService userService;

    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;

    }


    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list =  likeRepository.findByPostId(postId.get());
        } else
            list = likeRepository.findAll();
           return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getOneLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User dbUser = userService.getOneUser(likeCreateRequest.getUserId());
        Post dbPost = postService.getOnePost(likeCreateRequest.getPostId());
        if(dbUser != null && dbPost != null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(dbUser);
            likeToSave.setPost(dbPost);
            return likeRepository.save(likeToSave);
        }else{
            return null;
        }
    }


    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
