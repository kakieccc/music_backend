package com.kakie.music_backend.controller;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.InputStream;


@Controller
@Tag(name = "Minio模块")
@Slf4j
public class MinioController {
    @Value("${minio.bucket-name}")
    private String bucketName;
    @Resource
    private MinioClient minioClient;
    //获取歌曲
    @GetMapping("/song/{fileName:.+}")
    @Operation(summary = "获取歌曲")
    public ResponseEntity<byte[]> getMusic(@PathVariable String fileName) {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object("song/"+fileName)
                    .build();
            InputStream inputStream = minioClient.getObject(args);

            // 将文件内容读取为字节数组
            byte[] bytes = IOUtils.toByteArray(inputStream);

            // 设置响应头，指示浏览器如何处理响应内容
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName); // 如果需要下载文件，可以使用此头部

            // 返回字节数组作为响应
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //获取轮播图图片
    @GetMapping("/img/swiper/{fileName:.+}")
    @Operation(summary = "获取轮播图图片")
    public ResponseEntity<byte[]> getImage_swiper(@PathVariable String fileName) throws Exception {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object("img/swiper/"+fileName)
                        .build()
        );

        byte[] bytes = IOUtils.toByteArray(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应内容类型为图片类型，根据实际情况修改

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //获取歌手图片
    @GetMapping("/img/singerPic/{fileName:.+}")
    @Operation(summary = "获取歌手图片")
    public ResponseEntity<byte[]> getImage_singer(@PathVariable String fileName) throws Exception {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object("img/singerPic/"+fileName)
                        .build()
        );

        byte[] bytes = IOUtils.toByteArray(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应内容类型为图片类型，根据实际情况修改

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    //获取歌单图片
    @GetMapping("/img/songListPic/{fileName:.+}")
    @Operation(summary = "获取歌单图片")
    public ResponseEntity<byte[]> getImage_singList(@PathVariable String fileName) throws Exception {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object("img/songListPic/"+fileName)
                        .build()
        );

        byte[] bytes = IOUtils.toByteArray(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应内容类型为图片类型，根据实际情况修改

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //获取歌的图片
    @GetMapping("/img/songPic/{fileName:.+}")
    @Operation(summary = "获取歌的图片")
    public ResponseEntity<byte[]> getImage_song(@PathVariable String fileName) throws Exception {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object("img/songPic/"+fileName)
                        .build()
        );

        byte[] bytes = IOUtils.toByteArray(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应内容类型为图片类型，根据实际情况修改

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //获取头像
    @GetMapping("/img/avatorImages/{fileName:.+}")
    @Operation(summary = "获取头像")
    public ResponseEntity<byte[]> getImage_avatar(@PathVariable String fileName) throws Exception {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object("img/avatorImages/"+fileName)
                        .build()
        );

        byte[] bytes = IOUtils.toByteArray(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应内容类型为图片类型，根据实际情况修改

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
