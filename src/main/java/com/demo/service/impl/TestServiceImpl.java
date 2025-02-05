package com.demo.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.demo.config.MyConf;
import com.demo.domain.AddDto;
import com.demo.domain.param.AddParam;
import com.demo.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author shanli
 */
@Service
@Slf4j
public class TestServiceImpl implements ITestService {
    private final MyConf myConf;

    @Override
    public String hello(String name) {
        return StrUtil.format("{} {} {}", myConf.getVar1(), myConf.getVar2(), " hello");
    }

    @Override
    public AddDto add(AddParam addParam) {
        Assert.isTrue(addParam.getA() != null && addParam.getB() != null, "a或b不能为空");
        AddDto result = new AddDto();
        result.setStart(LocalDateTime.now());
        result.setAb(addParam.getA() + addParam.getB());
        result.setEnd(LocalDateTime.now());
        return result;
    }

    @Override
    public void addExport(AddParam addParam) {

    }
}
