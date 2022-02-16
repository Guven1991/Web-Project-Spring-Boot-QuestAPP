package com.guven.webprojectspringboot.response;

import com.guven.webprojectspringboot.entities.User;
import lombok.Data;

@Data
public class UserResponse {

    Long id;
    int avatarId;
    String username;

    public UserResponse(User entity){
        this.id = entity.getId();
        this.avatarId = entity.getAvatar();
        this.username = entity.getUsername();
    }

}
