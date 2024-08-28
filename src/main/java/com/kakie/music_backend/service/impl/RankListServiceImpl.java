package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.mapper.RankListMapper;
import com.kakie.music_backend.model.entity.RankList;
import com.kakie.music_backend.model.request.RankListRequest;
import com.kakie.music_backend.service.RankListService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RankListServiceImpl extends ServiceImpl<RankListMapper, RankList> implements RankListService {


    @Resource
    private RankListMapper rankMapper;

    @Override
    public BaseResponse addRank(RankListRequest rankListAddRequest) {
        RankList rankList = new RankList();
        BeanUtils.copyProperties(rankListAddRequest, rankList);
        if (rankMapper.insert(rankList) > 0) {
            return BaseResponse.success("评价成功");
        } else {
            return BaseResponse.error("评价失败");
        }
    }

    @Override
    public BaseResponse rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        QueryWrapper<RankList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        Long rankNum = rankMapper.selectCount(queryWrapper);
        return BaseResponse.success(null, (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum);
    }

    @Override
    public BaseResponse getUserRank(Long consumerId, Long songListId) {
        Integer i = rankMapper.selectUserRank(consumerId, songListId);
        return BaseResponse.success(null, i);
    }
}