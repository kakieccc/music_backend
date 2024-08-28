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
 * @TableName singer
 */
@TableName(value ="singer")
@Data
public class Singer implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer sex;

    /**
     * 
     */
    private String pic;

    /**
     * 
     */
    private Date birth;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private String introduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}