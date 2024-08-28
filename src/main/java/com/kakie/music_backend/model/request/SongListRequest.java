package com.kakie.music_backend.model.request;

import lombok.Data;

@Data
public class SongListRequest {
    private Integer id;

    private String title;

    private String pic;

    private String style;

    private String introduction;
}
