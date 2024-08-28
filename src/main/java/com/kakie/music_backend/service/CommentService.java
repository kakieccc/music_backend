package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.Comment;
import com.kakie.music_backend.model.request.CommentRequest;

public interface CommentService extends IService<Comment> {

    BaseResponse addComment(CommentRequest addCommentRequest);

    BaseResponse updateCommentMsg(CommentRequest upCommentRequest);

    BaseResponse deleteComment(Integer id);

    BaseResponse commentOfSongId(Integer songId);

    BaseResponse commentOfSongListId(Integer songListId);

}
