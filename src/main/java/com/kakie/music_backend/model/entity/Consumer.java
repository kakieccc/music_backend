package com.kakie.music_backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName consumer
 */
@TableName(value ="consumer")
@Data
public class Consumer implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Integer sex;

    /**
     * 
     */
    private String phoneNum;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Date birth;

    /**
     * 
     */
    private String introduction;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private String avator;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}