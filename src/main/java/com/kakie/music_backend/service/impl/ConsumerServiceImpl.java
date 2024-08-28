package com.kakie.music_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kakie.music_backend.common.BaseResponse;
import com.kakie.music_backend.constant.Constants;
import com.kakie.music_backend.controller.MinioUploadController;
import com.kakie.music_backend.mapper.ConsumerMapper;
import com.kakie.music_backend.model.entity.Consumer;
import com.kakie.music_backend.model.request.ConsumerRequest;
import com.kakie.music_backend.service.ConsumerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer>
        implements ConsumerService {

    @Resource
    private ConsumerMapper consumerMapper;


    /**
     * 新增用户
     */
    @Override
    public BaseResponse addUser(ConsumerRequest registryRequest) {
        if (this.existUser(registryRequest.getUsername())) {
            return BaseResponse.warning("用户名已注册");
        }
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(registryRequest, consumer);
        //MD5加密
        String password = DigestUtils.md5DigestAsHex((Constants.SALT + registryRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(password);

        if (StringUtils.isBlank(consumer.getPhoneNum())) {
            consumer.setPhoneNum(null);
        }
        if ("".equals(consumer.getEmail())) {
            consumer.setEmail(null);
        }
        consumer.setAvator("img/avatorImages/user.jpg");
        try {
            QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",consumer.getEmail());
            Consumer one = consumerMapper.selectOne(queryWrapper);
            if (one!=null){
                return BaseResponse.fatal("邮箱不允许重复");
            }
            if (consumerMapper.insert(consumer) > 0) {
                return BaseResponse.success("注册成功");
            } else {
                return BaseResponse.error("注册失败");
            }
        } catch (DuplicateKeyException e) {
            return BaseResponse.fatal(e.getMessage());
        }
    }

    @Override
    public BaseResponse updateUserMsg(ConsumerRequest updateRequest) {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(updateRequest, consumer);
        if (consumerMapper.updateById(consumer) > 0) {
            return BaseResponse.success("修改成功");
        } else {
            return BaseResponse.error("修改失败");
        }
    }

    @Override
    public BaseResponse updatePassword(ConsumerRequest updatePasswordRequest) {

       if (!this.verityPasswd(updatePasswordRequest.getUsername(),updatePasswordRequest.getOldPassword())) {
            return BaseResponse.error("密码输入错误");
        }

        Consumer consumer = new Consumer();
        consumer.setId(updatePasswordRequest.getId());
        String secretPassword = DigestUtils.md5DigestAsHex((Constants.SALT + updatePasswordRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(secretPassword);

        if (consumerMapper.updateById(consumer) > 0) {
            return BaseResponse.success("密码修改成功");
        } else {
            return BaseResponse.error("密码修改失败");
        }
    }

    @Override
    public BaseResponse updateUserAvator(MultipartFile avatorFile, int id) {
        String fileName = avatorFile.getOriginalFilename();
        String imgPath = "/img/avatorImages/" + fileName;
        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setAvator(imgPath);
        String s = MinioUploadController.uploadAtorImgFile(avatorFile);
        if (s.equals("File uploaded successfully!")&&consumerMapper.updateById(consumer) > 0) {
            return BaseResponse.success("上传成功", imgPath);
        } else {
            return BaseResponse.error("上传失败");
        }
    }

    @Override
    public boolean existUser(String username) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return consumerMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean verityPasswd(String username, String password) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        String secretPassword = DigestUtils.md5DigestAsHex((Constants.SALT + password).getBytes(StandardCharsets.UTF_8));

        queryWrapper.eq("password",secretPassword);
        return consumerMapper.selectCount(queryWrapper) > 0;
    }


    // 删除用户
    @Override
    public BaseResponse deleteUser(Integer id) {
        if (consumerMapper.deleteById(id) > 0) {
            return BaseResponse.success("删除成功");
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    @Override
    public BaseResponse allUser() {
        return BaseResponse.success(null, consumerMapper.selectList(null));
    }

    @Override
    public BaseResponse userOfId(Integer id) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return BaseResponse.success(null, consumerMapper.selectList(queryWrapper));
    }

    @Override
    public BaseResponse loginStatus(ConsumerRequest loginRequest, HttpSession session) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (this.verityPasswd(username, password)) {
            session.setAttribute("username", username);
            Consumer consumer = new Consumer();
            consumer.setUsername(username);
            return BaseResponse.success("登录成功", consumerMapper.selectList(new QueryWrapper<>(consumer)));
        } else {
            return BaseResponse.error("用户名或密码错误");
        }
    }

}
