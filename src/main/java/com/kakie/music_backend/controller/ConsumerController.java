package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.ConsumerRequest;
import com.kakie.music_backend.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "用户模块")
@Slf4j
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    /**
     * 用户注册
     */
    @PostMapping("/user/add")
    @Operation(summary = "用户注册")
    public BaseResponse addUser(@RequestBody ConsumerRequest registryRequest) {
        return consumerService.addUser(registryRequest);
    }

    /**
     * 登录判断
     */
    @PostMapping("/user/login/status")
    @Operation(summary = "登录判断")
    public BaseResponse loginStatus(@RequestBody ConsumerRequest loginRequest, HttpSession session) {
        return consumerService.loginStatus(loginRequest, session);
    }

    /**
     * 返回所有用户
     */
    @GetMapping("/user")
    @Operation(summary = "返回所有用户")
    public BaseResponse allUser() {
        return consumerService.allUser();
    }


    /**
     * 返回指定 ID 的用户
     */
    @GetMapping("/user/detail")
    @Operation(summary = "返回指定 ID 的用户")
    public BaseResponse userOfId(@RequestParam int id) {
        return consumerService.userOfId(id);
    }

    /**
     * 删除用户
     */
    @GetMapping("/user/delete")
    @Operation(summary = "删除用户")
    public BaseResponse deleteUser(@RequestParam int id) {
        return consumerService.deleteUser(id);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/user/update")
    @Operation(summary = "更新用户信息")
    public BaseResponse updateUserMsg(@RequestBody ConsumerRequest updateRequest) {
        return consumerService.updateUserMsg(updateRequest);
    }

    /**
     * 更新用户密码
     */
    @PostMapping("/user/updatePassword")
    @Operation(summary = "更新用户密码")
    public BaseResponse updatePassword(@RequestBody ConsumerRequest updatePasswordRequest) {
        return consumerService.updatePassword(updatePasswordRequest);
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/user/avatar/update")
    @Operation(summary = "更新用户头像")
    public BaseResponse updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return consumerService.updateUserAvator(avatorFile, id);
    }

}
