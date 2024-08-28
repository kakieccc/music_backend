package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.ListSong;
import com.kakie.music_backend.model.request.ListSongRequest;

import java.util.List;

public interface ListSongService extends IService<ListSong> {

    BaseResponse addListSong(ListSongRequest addListSongRequest);

    BaseResponse updateListSongMsg(ListSongRequest updateListSongRequest);

    BaseResponse deleteListSong(Integer songId);

    //看看这啥
    List<ListSong> allListSong();

    BaseResponse listSongOfSongId(Integer songListId);
}
