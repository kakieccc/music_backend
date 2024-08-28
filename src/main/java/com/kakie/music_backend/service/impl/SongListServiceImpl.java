package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.controller.MinioUploadController;
import com.kakie.music_backend.mapper.SongListMapper;
import com.kakie.music_backend.model.entity.SongList;
import com.kakie.music_backend.model.request.SongListRequest;
import com.kakie.music_backend.service.SongListService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Resource
    private SongListMapper songListMapper;
    @Value("${minio.bucket-name}")
    String bucketName;

    @Override
    public BaseResponse updateSongListMsg(SongListRequest updateSongListRequest) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(updateSongListRequest, songList);
        if (songListMapper.updateById(songList) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse deleteSongList(Integer id) {
        if (songListMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse allSongList() {
        return BaseResponse.success(null, songListMapper.selectList(null));
    }

    @Override
    public List<SongList> findAllSong() {
        List<SongList> songLists = songListMapper.selectList(null);
        return songLists;
    }


    @Override
    public BaseResponse likeTitle(String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return BaseResponse.success(null, songListMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse likeStyle(String style) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style",style);
        return BaseResponse.success(null, songListMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse addSongList(SongListRequest addSongListRequest) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(addSongListRequest, songList);
        String pic = "/img/songListPic/123.jpg";
        songList.setPic(pic);
        if (songListMapper.insert(songList) > 0) {
            return BaseResponse.success("添加成功");
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    @Override
    public BaseResponse updateSongListImg(MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName =avatorFile.getOriginalFilename();
        String path="/img/songListPic/";
        String imgPath = path + fileName;
        MinioUploadController.uploadSonglistImgFile(avatorFile);
        SongList songList = new SongList();
        songList.setId(id);
        songList.setPic(imgPath);
        if (songListMapper.updateById(songList) > 0) {
            return BaseResponse.success("上传成功", imgPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }
}
