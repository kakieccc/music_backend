package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.CommentRequest;
import com.kakie.music_backend.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "评论模块")
@Slf4j
public class CommentController {
    @Resource
    private CommentService commentService;


    // 提交评论
    @PostMapping("/comment/add")
    @Operation(summary = "提交评论")
    public BaseResponse addComment(@RequestBody CommentRequest addCommentRequest) {
        return commentService.addComment(addCommentRequest);
    }

    // 删除评论
    @GetMapping("/comment/delete")
    @Operation(summary = "删除评论")
    public BaseResponse deleteComment(@RequestParam Integer id) {
        return commentService.deleteComment(id);
    }

    // 获得指定歌曲 ID 的评论列表
    @GetMapping("/comment/song/detail")
    @Operation(summary = "获得指定歌曲 ID 的评论列表")
    public BaseResponse commentOfSongId(@RequestParam Integer songId) {
        return commentService.commentOfSongId(songId);
    }

    // 获得指定歌单 ID 的评论列表
    @GetMapping("/comment/songList/detail")
    @Operation(summary = "获得指定歌单 ID 的评论列表")
    public BaseResponse commentOfSongListId(@RequestParam Integer songListId) {
        return commentService.commentOfSongListId(songListId);
    }

    // 点赞
    @PostMapping("/comment/like")
    @Operation(summary = "点赞")
    public BaseResponse commentOfLike(@RequestBody CommentRequest upCommentRequest) {
        return commentService.updateCommentMsg(upCommentRequest);
    }
}
