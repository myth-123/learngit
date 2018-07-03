package com.hbzl.dev.ibom.common.result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 默认成功，不带具体额外数据
     * @return
     */
    public static <T> Result<T> genSuccessResult() {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 成功，带有返回的额外数据
     * @param data
     * @return
     */
    public static <T> Result<T> genSuccessResult(T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    /**
     * 成功，自定义消息及数据
     * @param message
     * @param data
     * @return
     */
    public static <T> Result<T> genSuccessResult(String message,T data) {
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }
    /**
     * 失败，附带消息
     * @param message
     * @return
     */
    public static <T> Result<T> genFailResult(String message) {
        return new Result<T>()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
    /**
     * 失败，自定义消息及数据
     * @param message
     * @param data
     * @return
     */
    public static <T> Result<T> genFailResult(String message, T data) {
        return new Result<T>()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }
    /**
     * 自定义创建返回内容
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> Result<T> genFreeResult(ResultCode code, String message, T data) {
        return new Result<T>()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}