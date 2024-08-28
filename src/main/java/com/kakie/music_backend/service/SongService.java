package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.Song;
import com.kakie.music_backend.model.request.SongRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SongService extends IService<Song> {

    BaseResponse addSong (SongRequest addSongRequest, MultipartFile lrcfile, MultipartFile mpfile);

    BaseResponse updateSongMsg(SongRequest updateSongRequest);

    BaseResponse updateSongUrl(MultipartFile urlFile, int id);

    BaseResponse updateSongPic(MultipartFile urlFile, int id);

    BaseResponse deleteSong(Integer id);

    BaseResponse allSong();

    BaseResponse songOfSingerId(Integer singerId);

    BaseResponse songOfId(Integer id);

    BaseResponse songOfSingerName(String name);

    BaseResponse updateSongLrc(MultipartFile lrcFile, int id);
}
