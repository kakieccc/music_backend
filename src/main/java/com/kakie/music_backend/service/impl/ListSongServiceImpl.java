package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.mapper.ListSongMapper;
import com.kakie.music_backend.model.entity.ListSong;
import com.kakie.music_backend.model.request.ListSongRequest;
import com.kakie.music_backend.service.ListSongService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

    @Resource
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.selectList(null);
    }

    @Override
    public BaseResponse updateListSongMsg(ListSongRequest updateListSongRequest) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(updateListSongRequest, listSong);
        if (listSongMapper.updateById(listSong) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse deleteListSong(Integer songId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",songId);
        if (listSongMapper.delete(queryWrapper) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse addListSong(ListSongRequest addListSongRequest) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(addListSongRequest, listSong);
        if (listSongMapper.insert(listSong) > 0) {
            return BaseResponse.success("添加成功");
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    @Override
    public BaseResponse listSongOfSongId(Integer songListId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return BaseResponse.success("查询成功", listSongMapper.selectList(queryWrapper));
    }

}
