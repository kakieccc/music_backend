package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.SongRequest;
import com.kakie.music_backend.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.MultipartConfigElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "歌曲模块")
@Slf4j
public class SongController {

    @Resource
    private SongService songService;


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }



    // 添加歌曲
    @PostMapping("/song/add")
    @Operation(summary = "添加歌曲")
    public BaseResponse addSong(SongRequest addSongRequest, @RequestParam("lrcfile") MultipartFile lrcfile, @RequestParam("file") MultipartFile mpfile) {
        return songService.addSong(addSongRequest,lrcfile,mpfile);
    }

    // 删除歌曲
    @DeleteMapping("/song/delete")
    @Operation(summary = "删除歌曲")
    public BaseResponse deleteSong(@RequestParam int id) {
        return songService.deleteSong(id);
    }

    // 返回所有歌曲
    @GetMapping("/song")
    @Operation(summary = "返回所有歌曲")
    public BaseResponse allSong() {
        return songService.allSong();
    }

    @GetMapping("/song/detail")
    @Operation(summary = "返回歌曲对象")
    public BaseResponse songOfId(@RequestParam int id) {
        return songService.songOfId(id);
    }

    // 返回指定歌手ID的歌曲
    @GetMapping("/song/singer/detail")
    @Operation(summary = "返回指定歌手ID的歌曲")
    public BaseResponse songOfSingerId(@RequestParam int singerId) {
        return songService.songOfSingerId(singerId);
    }


    // 返回指定歌手名的歌曲
    @GetMapping("/song/singerName/detail")
    @Operation(summary = "返回指定歌手名的歌曲")
    public BaseResponse songOfSingerName(@RequestParam String name) {
        return songService.songOfSingerName('%' + name + '%');
    }

    // 更新歌曲信息
    @PostMapping("/song/update")
    @Operation(summary = "更新歌曲信息")
    public BaseResponse updateSongMsg(@RequestBody SongRequest updateSongRequest) {
        return songService.updateSongMsg(updateSongRequest);
    }

    // 更新歌曲图片
    @PostMapping("/song/img/update")
    @Operation(summary = "更新歌曲图片")
    public BaseResponse updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongPic(urlFile, id);
    }

    // 更新歌曲
    @PostMapping("/song/url/update")
    @Operation(summary = "更新歌曲url")
    public BaseResponse updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongUrl(urlFile, id);
    }
    ///song/lrc/update
    //更新歌词
    @PostMapping("/song/lrc/update")
    @Operation(summary = "更新歌词")
    public BaseResponse updateSongLrc(@RequestParam("file") MultipartFile lrcFile, @RequestParam("id") int id) {
        return songService.updateSongLrc(lrcFile, id);
    }

}
