package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.Singer;
import com.kakie.music_backend.model.request.SingerRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SingerService extends IService<Singer> {

    BaseResponse addSinger (SingerRequest addSingerRequest);

    BaseResponse updateSingerMsg(SingerRequest updateSingerRequest);

    BaseResponse updateSingerPic(MultipartFile avatorFile, int id);

    BaseResponse deleteSinger(Integer id);

    BaseResponse allSinger();

    BaseResponse singerOfName(String name);

    BaseResponse singerOfSex(Integer sex);
}
