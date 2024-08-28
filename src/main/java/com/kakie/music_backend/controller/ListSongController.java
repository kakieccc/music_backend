package com.kakie.music_backend.controller;

import com.alibaba.excel.EasyExcel;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.SongList;
import com.kakie.music_backend.model.request.ListSongRequest;
import com.kakie.music_backend.service.ListSongService;
import com.kakie.music_backend.service.SongListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@Tag(name = "歌单歌曲模块")
@Slf4j
public class ListSongController {

    @jakarta.annotation.Resource
    private ListSongService listSongService;
    @jakarta.annotation.Resource
    private SongListService service;
    // 给歌单添加歌曲
    @PostMapping("/listSong/add")
    @Operation(summary = "给歌单添加歌曲")
    public BaseResponse addListSong(@RequestBody ListSongRequest addListSongRequest) {
        return listSongService.addListSong(addListSongRequest);
    }

    // 删除歌单里的歌曲
    @GetMapping("/listSong/delete")
    @Operation(summary = "删除歌单里的歌曲")
    public BaseResponse deleteListSong(@RequestParam int songId) {
        return listSongService.deleteListSong(songId);
    }

    // 返回歌单里指定歌单 ID 的歌曲
    @GetMapping("/listSong/detail")
    @Operation(summary = "返回歌单里指定歌单 ID 的歌曲")
    public BaseResponse listSongOfSongId(@RequestParam int songListId) {
        return listSongService.listSongOfSongId(songListId);
    }

    // 更新歌单里面的歌曲信息
    @PostMapping("/listSong/update")
    @Operation(summary = "更新歌单里面的歌曲信息")
    public BaseResponse updateListSongMsg(@RequestBody ListSongRequest updateListSongRequest) {
        return listSongService.updateListSongMsg(updateListSongRequest);
    }
    //导出歌单
    @GetMapping("/excle")
    @Operation(summary = "导出歌单")
    public ResponseEntity<Resource> getExcle(HttpServletRequest request) throws IOException {
        String fileName = "SongList" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, SongList.class).sheet("模板").doWrite(data());
        File file = new File(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        byte[] content = Files.readAllBytes(file.toPath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(content.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    private List<SongList> data() {
        List<SongList> allSong = service.findAllSong();
        return allSong;
    }

}
