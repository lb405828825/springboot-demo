package com.xh.demo_lb.service;

import com.xh.demo_lb.model.AccessLog;

import java.io.IOException;

public interface WriteLogService {
    void insertData(AccessLog accessLog) throws IOException;
    default void updateData(){};
}
