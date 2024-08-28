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
 * @TableName song
 */
@TableName(value ="song")
@Data
public class Song implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer singerId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String introduction;

    /**
     * 发行时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String pic;

    /**
     * 
     */
    private String lyric;

    /**
     * 
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}