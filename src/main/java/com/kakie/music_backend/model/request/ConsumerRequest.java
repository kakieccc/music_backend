package com.kakie.music_backend.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumerRequest {
    private Integer id;

    private String username;

    private String oldPassword;

    private String password;

    private Integer sex;

    private String phoneNum;

    private String email;

    private Date birth;

    private String introduction;

    private String location;

    private String avator;

    private Date createTime;
}
