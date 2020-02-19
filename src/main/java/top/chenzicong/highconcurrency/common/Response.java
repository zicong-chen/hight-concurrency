package top.chenzicong.highconcurrency.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@AllArgsConstructor
@Data
public class Response<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static Response SUCCESS = new Response<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), null);
    public static Response FAIL = new Response<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMessage(), null);


    public static <T> Response<T> success(T data) {
        return new Response<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static Response fail(Integer code, String message) {
        return new Response<>(code, message, null);
    }

    public static Response fail(StatusCode statusCode) {
        return new Response<>(statusCode.getCode(), statusCode.getMessage(), null);
    }

    public static Response fail(Integer code) {
        return fail(code, StatusCode.FAIL.getMessage());
    }

    public static Response fail(String message) {
        return fail(StatusCode.FAIL.getCode(), message);
    }


}
