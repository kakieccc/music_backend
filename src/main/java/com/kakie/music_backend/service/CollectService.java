package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.Collect;
import com.kakie.music_backend.model.request.CollectRequest;

public interface CollectService extends IService<Collect> {

    BaseResponse addCollection(CollectRequest addCollectRequest);

    BaseResponse existSongId(CollectRequest isCollectRequest);

    BaseResponse deleteCollect(Integer userId,Integer songId);

    BaseResponse collectionOfUser(Integer userId);
}
