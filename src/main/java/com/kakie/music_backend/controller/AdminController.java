package com.kakie.music_backend.controller;

import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.AdminRequest;
import com.kakie.music_backend.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理的相关事宜
 */
@RestController
@Tag(name = "后台管理模块")
@Slf4j
public class AdminController {
    @Resource
    private AdminService adminService;

    // 判断是否登录成功
    @PostMapping("/admin/login/status")
    @Operation(summary = "判断是否登录成功")
    public BaseResponse loginStatus(@RequestBody AdminRequest adminRequest, HttpSession session) {
        return adminService.verityPasswd(adminRequest, session);
    }
}
