package com.xh.demo_lb.model;

import lombok.Data;
import lombok.ToString;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/25
 */
@Data
@ToString
public class AccessLog {
    private String id;
    private String xm;
    private String zjhm;

    private String test_def1;
    private String test_def2;

    public AccessLog(String id, String xm, String zjhm, String test_def1, String test_def2) {
        this.id = id;
        this.xm = xm;
        this.zjhm = zjhm;

        this.test_def1=test_def1;
        this.test_def2=test_def2;
    }
}
