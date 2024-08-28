package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.controller.MinioUploadController;
import com.kakie.music_backend.mapper.SongMapper;
import com.kakie.music_backend.model.entity.Song;
import com.kakie.music_backend.model.request.SongRequest;
import com.kakie.music_backend.service.SongService;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Resource
    private SongMapper songMapper;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Resource
    MinioClient minioClient;

    @Override
    public BaseResponse allSong() {
        return BaseResponse.success(null, songMapper.selectList(null));
    }

    @Override
    public BaseResponse addSong(SongRequest addSongRequest, MultipartFile lrcfile, MultipartFile mpfile){
        Song song = new Song();
        BeanUtils.copyProperties(addSongRequest, song);
        String pic = "/img/songPic/tubiao.jpg";
        String fileName = mpfile.getOriginalFilename();
        String s = MinioUploadController.uploadFile(mpfile);
        String storeUrlPath = "/song/" + fileName;
        song.setCreateTime(new Date());
        song.setUpdateTime(new Date());
        song.setPic(pic);
        song.setUrl(storeUrlPath);

        if (lrcfile!=null&&(song.getLyric().equals("[00:00:00]暂无歌词"))){
            byte[] fileContent = new byte[0];
            try {
                fileContent = lrcfile.getBytes();
                String content = new String(fileContent, "GB2312");
                song.setLyric(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (s.equals("File uploaded successfully!")&&songMapper.insert(song) > 0) {
            return BaseResponse.success("上传成功", storeUrlPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public BaseResponse updateSongMsg(SongRequest updateSongRequest) {
        Song song = new Song();
        BeanUtils.copyProperties(updateSongRequest, song);
        if (songMapper.updateById(song) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse updateSongUrl(MultipartFile urlFile, int id) {
        Song song = songMapper.selectById(id);
        String path = song.getUrl();
        String[] parts = path.split("/");
        String fileName = parts[parts.length - 1];

        RemoveObjectArgs removeObjectArgs=RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        fileName = urlFile.getOriginalFilename();
        String s = MinioUploadController.uploadFile(urlFile);
        try {
            minioClient.removeObject(removeObjectArgs);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }
        String storeUrlPath = "/"+bucketName+"/" + fileName;
        song.setId(id);
        song.setUrl(storeUrlPath);
        song.setName(fileName);
        if (s.equals("File uploaded successfully!")&&songMapper.updateById(song) > 0) {
            return BaseResponse.success("更新成功", storeUrlPath);
        } else {
            return BaseResponse.error("更新失败");
        }
    }

    @Override
    public BaseResponse updateSongPic(MultipartFile urlFile, int id) {
        String fileName =  urlFile.getOriginalFilename();
        String storeUrlPath = "/user01/img/songPic/" + fileName;
        MinioUploadController.uploadSongImgFile(urlFile);
        Song song = new Song();
        song.setId(id);
        song.setPic(storeUrlPath);
        if (songMapper.updateById(song) > 0) {
            return BaseResponse.success("上传成功", storeUrlPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public BaseResponse deleteSong(Integer id) {
        Song song = songMapper.selectById(id);
        String path = song.getUrl();
        String[] parts = path.split("/");
        String fileName = parts[parts.length - 1];
        System.out.println(fileName);
        RemoveObjectArgs removeObjectArgs=RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        if (songMapper.deleteById(id) > 0) {
            try {
                minioClient.removeObject(removeObjectArgs);
            } catch (ErrorResponseException e) {
                throw new RuntimeException(e);
            } catch (InsufficientDataException e) {
                throw new RuntimeException(e);
            } catch (InternalException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidResponseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (ServerException e) {
                throw new RuntimeException(e);
            } catch (XmlParserException e) {
                throw new RuntimeException(e);
            }
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse songOfSingerId(Integer singerId) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id",singerId);
        return BaseResponse.success(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse songOfId(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return BaseResponse.success(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse songOfSingerName(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<Song> songs = songMapper.selectList(queryWrapper);
        if (songs.isEmpty()){
            return BaseResponse.error("添加失败，没有找到该歌,无法加入该歌单");
        }

        return BaseResponse.success(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse updateSongLrc(MultipartFile lrcFile, int id) {
        Song song = songMapper.selectById(id);
        if (lrcFile!=null&&!(song.getLyric().equals("[00:00:00]暂无歌词"))){
            byte[] fileContent = new byte[0];
            try {
                fileContent = lrcFile.getBytes();
                String content = new String(fileContent, "GB2312");
                song.setLyric(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (songMapper.updateById(song) > 0) {
            return BaseResponse.success("更新成功");
        } else {
            return BaseResponse.error("更新失败");
        }
    }
}
