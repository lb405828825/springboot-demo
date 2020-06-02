package com.xh.demo_lb.controller;

import com.xh.demo_lb.common.RestResponse;
import com.xh.demo_lb.model.AccessLog;
import com.xh.demo_lb.prometheus.PrometheusMetrics;
import com.xh.demo_lb.service.TestDbService;
import com.xh.demo_lb.service.WriteLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/upload")
    @ResponseBody
    public Map<String,Object> singleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        Map<String, Object> map = new HashMap<String, Object>();
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "请选择文件");
            map.put("upload", 0);
            map.put("message", "请选择文件");
//            return "redirect:uploadStatus";
            return map;
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/" + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message","成功上传"+file.getOriginalFilename());
            map.put("upload", 1);
            map.put("message", "成功上传");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
