package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.CollectRequest;
import com.kakie.music_backend.service.CollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "收藏模块")
@Slf4j
public class CollectController {

    @Resource
    private CollectService collectService;


    // 添加收藏的歌曲
    //前台界面逻辑
    @PostMapping("/collection/add")
    @Operation(summary = "收藏")
    public BaseResponse addCollection(@RequestBody CollectRequest addCollectRequest) {
        return collectService.addCollection(addCollectRequest);
    }

    @DeleteMapping("/collection/delete")
    @Operation(summary = "取消收藏")
    public BaseResponse deleteCollection(@RequestParam Integer userId, @RequestParam Integer songId) {
        return collectService.deleteCollect(userId, songId);
    }

    // 是否收藏歌曲
    @PostMapping("/collection/status")
    @Operation(summary = "判断是否收藏歌曲")
    public BaseResponse isCollection(@RequestBody CollectRequest isCollectRequest) {
        return collectService.existSongId(isCollectRequest);

    }

    // 返回的指定用户 ID 收藏的列表
    @GetMapping("/collection/detail")
    @Operation(summary = "获取指定用户 ID 收藏的列表")
    public BaseResponse collectionOfUser(@RequestParam Integer userId) {
        return collectService.collectionOfUser(userId);
    }
}
