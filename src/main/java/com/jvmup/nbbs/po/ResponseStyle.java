package com.jvmup.nbbs.po;

import javafx.geometry.Pos;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 15:11
 **/
public class ResponseStyle {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;     // 元数据
    private Object data;   // 响应内容

    public  ResponseStyle success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public  ResponseStyle success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public ResponseStyle failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public ResponseStyle failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }
    public class Meta {

        private boolean success;
        private String message;

        private Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
