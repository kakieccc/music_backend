package com.kakie.music_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.model.entity.UserSupport;
import com.kakie.music_backend.model.request.UserSupportRequest;

/**
 * @description 针对表【user_support】的数据库操作Service
 */
public interface UserSupportService extends IService<UserSupport> {

    BaseResponse isUserSupportComment(UserSupportRequest userSupportRequest);

    BaseResponse insertCommentSupport(UserSupportRequest userSupportRequest);

    BaseResponse deleteCommentSupport(UserSupportRequest userSupportRequest);
}
