package com.guven.webprojectspringboot.response;

import com.guven.webprojectspringboot.entities.Like;
import com.guven.webprojectspringboot.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String username;
    String title;
    String text;
    List<LikeResponse> postLikes;

    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = likes;
    }
}
