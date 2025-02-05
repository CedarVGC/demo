package com.demo.config;

import com.demo.domain.enums.ReturnCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shanli
 */
@Getter
@Setter
public class MyException extends RuntimeException {

    private Integer errorCode;
    private String errorMsg;

    public  MyException() {
        this.errorCode = ReturnCode.RC500.getCode();
        this.errorMsg = ReturnCode.RC500.getMessage();
    }

    public MyException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MyException(ReturnCode returnCode) {
        this.errorCode = returnCode.getCode();
        this.errorMsg = returnCode.getMessage();
    }
}
