package com.xjy.edu.util;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {

    /**
     * 响应消息
     */
    protected String message;
    /**
     * 响应编码
     */
    protected String code;
    /**
     * 响应数据
     */
    private T data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "ResponseData(message=" + getMessage() + ", code=" + getCode() + ", data=" + getData() + ")";
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }


    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ResponseData(String message, String code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData() {
    }
}
