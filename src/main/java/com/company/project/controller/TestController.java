package com.company.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHL
 * @version 1.0
 * @date 2023/4/21 14:11
 * @since : JDK 11
 */
@Api(tags = "测试")
@RestController
public class TestController {


    @ApiOperation("欢迎页")
    @GetMapping("/index")
    public String index() {
        return "Hello World!";
    }


}
