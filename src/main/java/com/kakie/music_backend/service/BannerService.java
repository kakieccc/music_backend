package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.model.entity.Banner;

import java.util.List;

public interface BannerService extends IService<Banner> {

    List<Banner> getAllBanner();

}
