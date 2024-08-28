package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.mapper.UserSupportMapper;
import com.kakie.music_backend.model.entity.UserSupport;
import com.kakie.music_backend.model.request.UserSupportRequest;
import com.kakie.music_backend.service.UserSupportService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @description 针对表【user_support】的数据库操作Service实现
 */
@Service
public class UserSupportServiceImpl extends ServiceImpl<UserSupportMapper, UserSupport>
        implements UserSupportService {

    @Resource
    private UserSupportMapper userSupportMapper;

    @Override
    public BaseResponse isUserSupportComment(UserSupportRequest userSupportRequest) {
        QueryWrapper<UserSupport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", userSupportRequest.getCommentId());
        queryWrapper.eq("user_id", userSupportRequest.getUserId());
        if (userSupportMapper.selectCount(queryWrapper) > 0) {
            return BaseResponse.success("您已取消点赞", true);
        } else {
            return BaseResponse.success("您已点赞", false);
        }
    }

    @Override
    public BaseResponse insertCommentSupport(UserSupportRequest userSupportRequest) {
        UserSupport userSupport = new UserSupport();
        BeanUtils.copyProperties(userSupportRequest, userSupport);
        if (userSupportMapper.insert(userSupport) > 0) {
            return BaseResponse.success("添加记录成功");
        }
        return BaseResponse.error("添加时,发生异常");
    }

    @Override
    public BaseResponse deleteCommentSupport(UserSupportRequest userSupportRequest) {
        QueryWrapper<UserSupport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", userSupportRequest.getCommentId());
        queryWrapper.eq("user_id", userSupportRequest.getUserId());
        if (userSupportMapper.delete(queryWrapper) > 0) {
            return BaseResponse.success("删除记录成功");
        }
        return BaseResponse.error("删除记录发生异常");
    }
}
