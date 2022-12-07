package com.example.myapplication.common;

import java.io.Serializable;

/**
 * @author mzh
 */
// @Data
// @ToString
// @Accessors(chain = true)
// @NoArgsConstructor
// @AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3633492718999367551L;

    public static final String SUCCESS = "SUCCESS";

    /**
     * Error code
     */
    private boolean success;

    /**
     * Error code
     */
    private String code;

    /**
     * error message
     */
    private String msg;

    /**
     * return model
     */
    private T data;

    /**
     * 成功 result
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS)
                .setMsg(SUCCESS)
                .setData(data)
                .setSuccess(true);
        return result;
    }

    /**
     * 失败Result
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> bizFail(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code)
                .setMsg(msg)
                .setSuccess(true);
        return result;
    }

    /**
     * 失败Result
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code)
                .setMsg(msg)
                .setSuccess(false);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

