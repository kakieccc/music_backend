package com.kakie.music_backend.model.request;

import lombok.Data;

@Data
public class UserSupportRequest {
    Integer id;
    Integer commentId;
    Integer userId;
}
