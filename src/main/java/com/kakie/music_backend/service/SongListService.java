package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.SongList;
import com.kakie.music_backend.model.request.SongListRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SongListService extends IService<SongList> {

    BaseResponse addSongList(SongListRequest addSongListRequest);

    BaseResponse updateSongListMsg(SongListRequest updateSongListRequest);

    BaseResponse updateSongListImg(MultipartFile avatorFile, int id);

    BaseResponse deleteSongList(Integer id);

    BaseResponse allSongList();

    List<SongList> findAllSong();

    BaseResponse likeTitle(String title);

    BaseResponse likeStyle(String style);
}
