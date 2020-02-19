package top.chenzicong.highconcurrency.interceptor;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.HandlerInterceptor;
import top.chenzicong.highconcurrency.common.StatusCode;
import top.chenzicong.highconcurrency.exception.MyException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UploadFileIntercepter implements HandlerInterceptor {
    @Value("${real-max-file-size}")
    private DataSize maxSize;

    /**
     *  spring 的filesize是在请求未进入controller之前就进行判断的，所以，他所报出的异常并不能被ControllerAdvice正常捕获
     *  所以我们需要手动获取大小并且进行判断。
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request!=null && ServletFileUpload.isMultipartContent(request)){
        ServletRequestContext requestContext =  new  ServletRequestContext(request);
            long length = requestContext.contentLength();
            if(length>maxSize.toBytes()){
                throw new MyException(StatusCode.OUT_OF_MAX_SIZE);
            }
        }
        return true;
    }
}
