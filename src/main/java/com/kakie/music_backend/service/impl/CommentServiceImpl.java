package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.mapper.CommentMapper;
import com.kakie.music_backend.model.entity.Comment;
import com.kakie.music_backend.model.request.CommentRequest;
import com.kakie.music_backend.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public BaseResponse addComment(CommentRequest addCommentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentRequest, comment);
        comment.setType(addCommentRequest.getNowType());
        if (commentMapper.insert(comment) > 0) {
            return BaseResponse.success("评论成功");
        } else {
            return BaseResponse.error("评论失败");
        }
    }

    @Override
    public BaseResponse updateCommentMsg(CommentRequest addCommentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentRequest, comment);
        if (commentMapper.updateById(comment) > 0) {
            return BaseResponse.success("点赞成功");
        } else {
            return BaseResponse.error("点赞失败");
        }
    }

    //    删除评论
    @Override
    public BaseResponse deleteComment(Integer id) {
        if (commentMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse commentOfSongId(Integer songId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",songId);
        return BaseResponse.success(null, commentMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse commentOfSongListId(Integer songListId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return BaseResponse.success(null, commentMapper.selectList(queryWrapper));
    }
}
