package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.Consumer;
import com.kakie.music_backend.model.request.ConsumerRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public interface ConsumerService extends IService<Consumer> {

    BaseResponse addUser(ConsumerRequest registryRequest);

    BaseResponse updateUserMsg(ConsumerRequest updateRequest);

    BaseResponse updateUserAvator(MultipartFile avatorFile, int id);

    BaseResponse updatePassword(ConsumerRequest updatePasswordRequest);

    boolean existUser(String username);

    boolean verityPasswd(String username, String password);

    BaseResponse deleteUser(Integer id);

    BaseResponse allUser();

    BaseResponse userOfId(Integer id);

    BaseResponse loginStatus(ConsumerRequest loginRequest, HttpSession session);
}
