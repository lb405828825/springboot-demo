package com.xh.demo_lb.common;


/**
 * Created by Shannon on 2020/2/23.
 */
public class ApiRespUtils {
    public ApiRespUtils() {
    }

    public static <T> ApiResp<T> response(String code, String message, T data, String version, boolean isSuccess) {
        return new ApiResp(code, message, data, version, isSuccess);
    }

    public static <T> ApiResp<T> response(String code, String message, T data) {
        return new ApiResp(code, message, data);
    }

    public static <T> ApiResp<T> response(Result result, T data) {
        return response(result.getCode(), result.getDesc(), data);
    }

    public static <T> ApiResp<T> success(T data) {
        return response(Result.SUCCESS, data);
    }

    public static <T> ApiResp<T> responseWithVersion(Result result, T data) {
        boolean isSuccess = false;
        if (result.getDesc().equals(Result.SUCCESS.getDesc())) {
            isSuccess = true;
        }
        return response(result.getCode(), result.getDesc(), data, "0.1", isSuccess);
    }

    public static <T> ApiResp<T> responseWithVersion(Result result, String message, T data) {
        boolean isSuccess = false;
        if (result.getDesc().equals(Result.SUCCESS.getDesc())) {
            isSuccess = true;
        }
        return response(result.getCode(), message, data, "0.1", isSuccess);
    }

    public static <T> ApiResp<T> successWithVersion(T data) {
        return responseWithVersion(Result.SUCCESS200, data);
    }


}
