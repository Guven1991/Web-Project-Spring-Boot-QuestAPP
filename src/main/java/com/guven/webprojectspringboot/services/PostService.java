package com.guven.webprojectspringboot.services;

import com.guven.webprojectspringboot.entities.Post;
import com.guven.webprojectspringboot.entities.User;
import com.guven.webprojectspringboot.repos.PostRepository;
import com.guven.webprojectspringboot.requests.PostCreateRequest;
import com.guven.webprojectspringboot.requests.PostUpdateRequest;
import com.guven.webprojectspringboot.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {


    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService =userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }
        list = postRepository.findAll();
         return list.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    public Post getOnePost(Long postId) {
        return  postRepository.findById(postId).orElse(null);
    }


    public Post createOnePost(PostCreateRequest postCreateRequest) {
        User dbUser = userService.getOneUser(postCreateRequest.getUserId());
        if(dbUser == null){
            return null;
        }
        Post toSave = new Post();
        toSave.setId(postCreateRequest.getId());
        toSave.setText(postCreateRequest.getText());
        toSave.setTitle(postCreateRequest.getTitle());
        toSave.setUser(dbUser);
        return postRepository.save(toSave);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
               Optional<Post> dbPost = postRepository.findById(postId);
               if(dbPost.isPresent()){
                    Post toUpdate = dbPost.get();
                    toUpdate.setText(postUpdateRequest.getText());
                    toUpdate.setTitle(postUpdateRequest.getTitle());
                    postRepository.save(toUpdate);
                    return toUpdate;
               }
               return null;
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
