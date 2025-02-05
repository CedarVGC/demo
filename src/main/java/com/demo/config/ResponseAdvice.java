package com.demo.config;

import com.demo.domain.base.ResultData;
import com.demo.domain.enums.ReturnCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jam
 * @date 2021/7/8 10:10 上午
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.demo.controller"})
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("{}", serverHttpRequest.getURI());
        if (o instanceof String) {
            return objectMapper.writeValueAsString(ResultData.success(o));
        }
        return ResultData.success(o);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        //hasErrors()返回异常是否包含错误信息
        if (e.hasErrors()) {
            //获取异常错误信息，并添加到list集合中
            List<ObjectError> allErrors = e.getAllErrors();
            for (ObjectError objectError : allErrors) {
                list.add(objectError.getDefaultMessage());
            }
        }
        return ResultData.fail(ReturnCode.RC500.getCode(),list.toString());
    }


    @ExceptionHandler(value = MyException.class)
    public ResultData myExceptionHandler(MyException mex) {
        log.error("自定义异常: ", mex);
        return ResultData.fail(mex.getErrorCode(), mex.getErrorMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultData exceptionHandler(Exception ex) {
        log.error("异常: ", ex);
        return ResultData.fail(ReturnCode.RC500.getCode(), ex.getMessage());
    }





}
