package top.chenzicong.highconcurrency.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import top.chenzicong.highconcurrency.common.Response;

import java.util.List;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public Response MyException(MyException exception) {
        return Response.fail(exception.getMessage());
    }

    //截取绑定异常的信息。
    @ExceptionHandler(BindException.class)
    public Response MyException(BindException exception) {
        List<ObjectError> errors = exception.getAllErrors();
        ObjectError objectError = errors.get(0);
        return Response.fail(objectError.getDefaultMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Response handleError1(MultipartException e) {
        return Response.fail(e.getMessage());

    }

    @ExceptionHandler(RuntimeException.class)
    public Response RunTimeException(RuntimeException exception) {
        return Response.fail(exception.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public Response RunTimeException(Exception exception) {
        return Response.fail(exception.getMessage());

    }
}
