package com.guven.webprojectspringboot.controllers;

import com.guven.webprojectspringboot.entities.Post;
import com.guven.webprojectspringboot.requests.PostCreateRequest;
import com.guven.webprojectspringboot.requests.PostUpdateRequest;
import com.guven.webprojectspringboot.response.PostResponse;
import com.guven.webprojectspringboot.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {

        this.postService = postService;
    }

    //hem userId ye göre gelen postlar hemde tüm postlar için kontroller
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public PostResponse getOnePost(@PathVariable Long postId) {
        return postService.getOnePostByIdWithLikes(postId);
    }

    //Post oluşturulurken user aihtiyac duyduğu için requestte onunu user ıd sini de katarak yolluyoruz
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest postCreateRequest) {
        return postService.createOnePost(postCreateRequest);
    }

    @PutMapping("/{postId}")
    public Post updateonePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updateOnePost(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePost(postId);
    }
}
