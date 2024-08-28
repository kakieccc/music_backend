package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.SingerRequest;
import com.kakie.music_backend.service.SingerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "歌手模块")
@Slf4j
public class SingerController {

    @Resource
    private SingerService singerService;


    // 添加歌手
    @PostMapping("/singer/add")
    @Operation(summary = "添加歌手")
    public BaseResponse addSinger(@RequestBody SingerRequest addSingerRequest) {
        return singerService.addSinger(addSingerRequest);
    }

    // 删除歌手
    @DeleteMapping("/singer/delete")
    @Operation(summary = "删除歌手")
    public BaseResponse deleteSinger(@RequestParam int id) {
        return singerService.deleteSinger(id);
    }

    // 返回所有歌手
    @GetMapping("/singer")
    @Operation(summary = "返回所有歌手")
    public BaseResponse allSinger() {
        return singerService.allSinger();
    }

    // 根据歌手名查找歌手
    @GetMapping("/singer/name/detail")
    @Operation(summary = "根据歌手名查找歌手")
    public BaseResponse singerOfName(@RequestParam String name) {
        return singerService.singerOfName(name);
    }

    // 根据歌手性别查找歌手
    @GetMapping("/singer/sex/detail")
    @Operation(summary = "根据歌手性别查找歌手")
    public BaseResponse singerOfSex(@RequestParam int sex) {
        return singerService.singerOfSex(sex);
    }

    // 更新歌手信息
    @PostMapping("/singer/update")
    @Operation(summary = "更新歌手信息")
    public BaseResponse updateSingerMsg(@RequestBody SingerRequest updateSingerRequest) {
        return singerService.updateSingerMsg(updateSingerRequest);
    }

    // 更新歌手头像
    @PostMapping("/singer/avatar/update")
    @Operation(summary = "更新歌手头像")
    public BaseResponse updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return singerService.updateSingerPic(avatorFile, id);
    }
}
