package com.xh.demo_lb.common;

import lombok.Data;
import lombok.ToString;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/25
 */
@Data
@ToString
public class RestResponse {
    private int result;
    private String message;
}