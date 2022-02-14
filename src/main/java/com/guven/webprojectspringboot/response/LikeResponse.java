package com.guven.webprojectspringboot.response;

import com.guven.webprojectspringboot.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    Long id;
    Long postId;
    Long userId;

    public LikeResponse(Like entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
