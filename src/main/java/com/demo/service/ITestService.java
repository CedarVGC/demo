package com.demo.service;


import com.demo.domain.AddDto;
import com.demo.domain.param.AddParam;

/**
 * @author shanli
 */
public interface ITestService {

    String hello(String name);

    AddDto add(AddParam addParam);

    void addExport(AddParam addParam);
}
