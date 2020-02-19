package top.chenzicong.highconcurrency.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum StatusCode {

    //通用模块
    SUCCESS(20000, "请求成功"),
    FAIL(50000, "系统错误"),

    //登录模块 5002XX
    MOBILE_NOT_EXIT(500210, "手机号不存在"),
    PASSWORD_ERROR(500211,"密码错误"),

    //授权模块5003XX
    REPETITIVE_OPERATION(500301,"重复操作"),
    UNAUTHORIZED(500302,"未授权,token失效或未登录"),
    WITHOUT_PERMISSION(500303,"无该接口的权限"),
    //上传模块
    FILE_EMPTY(500401,"上传的是空文件"),
    UPLOAD_FAIL(500402,"上传失败"),
    OUT_OF_MAX_SIZE(500403,"文件过大"),
    ;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
