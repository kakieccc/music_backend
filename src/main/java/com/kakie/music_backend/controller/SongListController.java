package com.kakie.music_backend.controller;


import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.SongListRequest;
import com.kakie.music_backend.service.SongListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "歌单模块")
@Slf4j
public class SongListController {

    @Resource
    private SongListService songListService;


    // 添加歌单
    @PostMapping("/songList/add")
    @Operation(summary = "添加歌单")
    public BaseResponse addSongList(@RequestBody SongListRequest addSongListRequest) {
        return songListService.addSongList(addSongListRequest);
    }

    // 删除歌单
    @GetMapping("/songList/delete")
    @Operation(summary = "删除歌单")
    public BaseResponse deleteSongList(@RequestParam int id) {
        return songListService.deleteSongList(id);
    }

    @GetMapping("/songList")
    @Operation(summary = "返回所有歌单")
    public BaseResponse allSongList() {
        return songListService.allSongList();
    }

    // 返回标题包含文字的歌单
    @GetMapping("/songList/likeTitle/detail")
    public BaseResponse songListOfLikeTitle(@RequestParam String title) {
        return songListService.likeTitle('%' + title + '%');
    }

    // 返回指定类型的歌单
    @GetMapping("/songList/style/detail")
    @Operation(summary = "返回指定类型的歌单")
    public BaseResponse songListOfStyle(@RequestParam String style) {
        return songListService.likeStyle('%' + style + '%');
    }

    // 更新歌单信息
    @PostMapping("/songList/update")
    @Operation(summary = "更新歌单信息")
    public BaseResponse updateSongListMsg(@RequestBody SongListRequest updateSongListRequest) {
        return songListService.updateSongListMsg(updateSongListRequest);

    }

    // 更新歌单图片
    @PostMapping("/songList/img/update")
    @Operation(summary = "更新歌单图片")
    public BaseResponse updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {

        return songListService.updateSongListImg(avatorFile,id);
    }
}
