package com.xh.demo_lb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/26
 */
@Api("Login Controller")
@Controller //不能使用RestController,否则不能使用模板index。ResponseBody + Controller
public class LoginController {

    @ApiOperation(value = "index 页面")
    @GetMapping("/index")
    public String testIndex(){
//        System.out.println("test count")；
        return "index";
    }
}
