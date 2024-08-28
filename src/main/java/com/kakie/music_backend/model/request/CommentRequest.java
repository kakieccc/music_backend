package com.kakie.music_backend.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class CommentRequest {
    private Integer id;

    private Integer userId;

    private Integer songId;

    private Integer songListId;

    private String content;

    private Date createTime;

    private Integer nowType;

    private Integer up;//点赞
}
