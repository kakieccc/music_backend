package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.RankListRequest;
import com.kakie.music_backend.service.RankListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "评分模块")
@Slf4j
public class RankListController {

    @Resource
    private RankListService rankListService;


    // 提交评分
    @PostMapping("/rankList/add")
    @Operation(summary = "提交评分")
    public BaseResponse addRank(@RequestBody RankListRequest rankListAddRequest) {
        return rankListService.addRank(rankListAddRequest);
    }

    // 获取指定歌单的评分
    @GetMapping("/rankList")
    @Operation(summary = "获取指定歌单的评分")
    public BaseResponse rankOfSongListId(@RequestParam Long songListId) {
        return rankListService.rankOfSongListId(songListId);
    }

    // 获取指定用户的歌单评分
    @GetMapping("/rankList/user")
    @Operation(summary = "获取指定用户的歌单评分")
    public BaseResponse getUserRank(@RequestParam(required = false) Long consumerId, @RequestParam Long songListId) {
        BaseResponse userRank = rankListService.getUserRank(consumerId, songListId);
        return BaseResponse.success("成功",userRank);
    }


}