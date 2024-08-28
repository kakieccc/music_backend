package com.kakie.music_backend.common;
import lombok.Data;

/**
 * 通用返回类
 */
@Data
public class BaseResponse {

    private int code;

    private String message;

    private String type;

    private Boolean success;

    private Object data;

    public static BaseResponse success(String message) {
        BaseResponse r = new BaseResponse();
        r.setCode(200);
        r.setMessage(message);
        r.setSuccess(true);
        r.setType("success");
        r.setData(null);
        return r;
    }

    public static BaseResponse success(String message, Object data) {
        BaseResponse r = success(message);
        r.setData(data);
        return r;
    }

    public static BaseResponse warning(String message) {
        BaseResponse r = error(message);
        r.setType("warning");
        return r;
    }

    public static BaseResponse error(String message) {
        BaseResponse r = success(message);
        r.setSuccess(false);
        r.setType("error");
        return r;
    }

    public static BaseResponse fatal(String message) {
        BaseResponse r = error(message);
        r.setCode(500);
        return r;
    }
}

