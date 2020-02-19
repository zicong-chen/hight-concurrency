package top.chenzicong.highconcurrency.exception;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import top.chenzicong.highconcurrency.common.StatusCode;

public class MyException extends RuntimeException {
    private Integer code;

    public MyException() {
        this(StatusCode.FAIL.getMessage(), StatusCode.FAIL.getCode());
    }

    public MyException(String message) {
        this(message, StatusCode.FAIL.getCode());
    }

    public MyException(StatusCode statusCode) {
        this(statusCode.getMessage(), statusCode.getCode());
    }

    public MyException(String message, Integer code) {
        super(message, null, false, false);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
