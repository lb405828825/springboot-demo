package com.xh.demo_lb.controller;

import com.xh.demo_lb.common.RestResponse;
import com.xh.demo_lb.model.AccessLog;
import com.xh.demo_lb.prometheus.PrometheusMetrics;
import com.xh.demo_lb.service.TestDbService;
import com.xh.demo_lb.service.WriteLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@Api("Web test controller API")
@RestController
@RequestMapping("/v1")
public class WebTest {
//    private static final Logger logger = LoggerFactory.getLogger(WebTest.class);
    @Resource
    private TestDbService testDbSer;

    @Resource
    private WriteLogService writeLogService;

    @ApiOperation(value = "测试查询hyperbase")
    @RequestMapping("/query")
    public String testCount(@RequestParam(value = "id") String id){
//        System.out.println("test count")；
        PrometheusMetrics.TEST_COUNT.labels("count-1").inc();
        String result = null;
        try {
            result = testDbSer.printSelectTest(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "测试 insert hyperbase")
    @GetMapping("/insert")
    public RestResponse insertData(@RequestParam(value = "id", required = true) String id,
                                   @RequestParam(value = "xm") String xm,
                                   @RequestParam("zjhm") String zjhm,
                                   @RequestParam("test_def1") String test_def1,
                                   @RequestParam("test_def2") String test_def2){
        AccessLog accessLog = new AccessLog(id, xm, zjhm, test_def1, test_def2);
        try {
            writeLogService.insertData(accessLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return successResponse();
    }

    private RestResponse successResponse() {
        RestResponse response = new RestResponse();
        response.setResult(0);
        response.setMessage("操作成功");
        return response;
    }
}
