package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.mapper.CollectMapper;
import com.kakie.music_backend.model.entity.Collect;
import com.kakie.music_backend.model.request.CollectRequest;
import com.kakie.music_backend.service.CollectService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Resource
    private CollectMapper collectMapper;

    @Override
    public BaseResponse addCollection(CollectRequest addCollectRequest) {
        //用type来判断收藏的是歌还是歌单
        Collect collect = new Collect();
        BeanUtils.copyProperties(addCollectRequest, collect);
        if (collectMapper.insert(collect) > 0) {
            return BaseResponse.success("收藏成功", true);
        } else {
            return BaseResponse.error("收藏失败");
        }
    }

    @Override
    public BaseResponse existSongId(CollectRequest isCollectRequest) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",isCollectRequest.getUserId());
        queryWrapper.eq("song_id",isCollectRequest.getSongId());
        if (collectMapper.selectCount(queryWrapper) > 0) {
            return BaseResponse.success("已收藏", true);
        } else {
            return BaseResponse.success("未收藏", false);
        }
    }

    @Override
    public BaseResponse deleteCollect(Integer userId, Integer songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        if (collectMapper.delete(queryWrapper) > 0) {
            return BaseResponse.success("取消收藏", false);
        } else {
            return BaseResponse.error("取消收藏失败");
        }
    }

    @Override
    public BaseResponse collectionOfUser(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return BaseResponse.success("用户收藏", collectMapper.selectList(queryWrapper));
    }
}
