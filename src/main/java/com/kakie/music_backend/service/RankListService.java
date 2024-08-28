package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.RankList;
import com.kakie.music_backend.model.request.RankListRequest;

public interface RankListService extends IService<RankList> {

    BaseResponse addRank(RankListRequest rankListAddRequest);

    BaseResponse rankOfSongListId(Long songListId);

    BaseResponse getUserRank(Long consumerId, Long songListId);

}