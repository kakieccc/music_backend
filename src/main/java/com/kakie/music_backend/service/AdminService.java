package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.request.AdminRequest;
import jakarta.servlet.http.HttpSession;
import com.kakie.music_backend.model.entity.Admin;


public interface AdminService extends IService<Admin> {

    BaseResponse verityPasswd(AdminRequest adminRequest, HttpSession session);
}
