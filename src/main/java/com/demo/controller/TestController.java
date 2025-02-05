package com.demo.controller;

import cn.hutool.core.lang.Assert;
import com.demo.config.MyException;
import com.demo.domain.AddDto;
import com.demo.domain.enums.ReturnCode;
import com.demo.domain.param.AddParam;
import com.demo.service.ITestService;
import io.swagger.annotations.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author shanli
 */
@RestController
@Slf4j
@Api(tags = "测试接口")
@RequestMapping("/test")
public class TestController {
    private final ITestService testService;

    @ApiOperation("你好")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query", example = "admin"),
    })
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false) String name) {
        return testService.hello(name);
    }

    @ApiOperation("加法")
    @PostMapping("/add")
    public AddDto add(@RequestBody @Validated AddParam addParam) {
        return testService.add(addParam);
    }

    @ApiOperation("加法导出")
    @PostMapping("/addExport")
    public void addExport(@Validated AddParam addParam) {
       testService.addExport(addParam);
    }
}
