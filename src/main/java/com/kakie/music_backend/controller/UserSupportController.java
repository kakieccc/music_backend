package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.UserSupportRequest;
import com.kakie.music_backend.service.UserSupportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userSupport")
@Tag(name = "用户点赞模块")
@Slf4j
public class UserSupportController {

    @Resource
    UserSupportService userSupportService;

    @PostMapping("/test")
    @Operation(summary = "判断是否已经点赞")
    public BaseResponse isUserSupportComment(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.isUserSupportComment(userSupportRequest);
    }

    @PostMapping("/insert")
    @Operation(summary = "点赞评论")
    public BaseResponse insertCommentSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.insertCommentSupport(userSupportRequest);
    }

    @PostMapping("/delete")
    @Operation(summary = "取消点赞评论")
    public BaseResponse deleteCommentSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.deleteCommentSupport(userSupportRequest);
    }
}
