package com.xh.demo_lb.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Shannon on 2020/2/23.
 */
@ApiModel("Api Data Structure")
public class ApiResp<T> {
    @ApiModelProperty(
            value = "code",
            required = true
    )
    private String code;
    @ApiModelProperty("reponse message")
    private String message;
    @ApiModelProperty("response data")
    private T data;
    @ApiModelProperty("version")
    String version;
    @ApiModelProperty("isSuccess")
    boolean isSuccess;

    public ApiResp() {
    }

    public ApiResp(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResp(String code, String message, T data, String version, boolean isSuccess) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.version = version;
        this.isSuccess = isSuccess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
