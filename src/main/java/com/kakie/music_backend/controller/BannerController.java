package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
@Tag(name = "轮播图模块")
@Slf4j
public class BannerController {

    @Resource
    private BannerService bannerService;

    @GetMapping("/getAllBanner")
    @Operation(summary = "获取所有轮播图")
    public BaseResponse getAllBanner(){
        return BaseResponse.success("成功获取轮播图与",bannerService.getAllBanner());
    }
}
