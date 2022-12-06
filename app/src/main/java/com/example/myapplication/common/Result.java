package com.example.myapplication.common;

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    private static final String SUCCESS = "success";

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg("ok");
        result.setCode(SUCCESS);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}