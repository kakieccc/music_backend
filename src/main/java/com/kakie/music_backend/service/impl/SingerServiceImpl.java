package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.controller.MinioUploadController;
import com.kakie.music_backend.mapper.SingerMapper;
import com.kakie.music_backend.model.entity.Singer;
import com.kakie.music_backend.model.request.SingerRequest;
import com.kakie.music_backend.service.SingerService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Resource
    private SingerMapper singerMapper;

    @Override
    public BaseResponse updateSingerMsg(SingerRequest updateSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(updateSingerRequest, singer);
        if (singerMapper.updateById(singer) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse updateSingerPic(MultipartFile avatorFile, int id) {
        String fileName =  avatorFile.getOriginalFilename();
        MinioUploadController.uploadSingerImgFile(avatorFile);
        String imgPath = "/img/singerPic/" + fileName;
        Singer singer = new Singer();
        singer.setId(id);
        singer.setPic(imgPath);
        if (singerMapper.updateById(singer) > 0) {
            return BaseResponse.success("上传成功", imgPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public BaseResponse deleteSinger(Integer id) {
        if (singerMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse allSinger() {
        return BaseResponse.success(null, singerMapper.selectList(null));
    }

    @Override
    public BaseResponse addSinger(SingerRequest addSingerRequest) {
        Singer singer = new Singer();
        String pic = "/img/avatorImages/user.jpg";
        singer.setPic(pic);
        BeanUtils.copyProperties(addSingerRequest, singer);
        if (singerMapper.insert(singer) > 0) {
            return BaseResponse.success("添加成功");
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    @Override
    public BaseResponse singerOfName(String name) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return BaseResponse.success(null, singerMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse singerOfSex(Integer sex) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sex", sex);
        return BaseResponse.success(null, singerMapper.selectList(queryWrapper));
    }
}
