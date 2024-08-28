package com.kakie.music_backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName song_list
 */
@TableName(value ="song_list")
@Data
public class SongList implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String pic;

    /**
     * 
     */
    private String introduction;

    /**
     * 
     */
    private String style;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}