package top.chenzicong.highconcurrency.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.chenzicong.highconcurrency.common.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        response.setCharacterEncoding("UTF-8");
        //   response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String responseBody = objectMapper.writeValueAsString(Response.FAIL);
//        writer.println(responseBody);
        return true;
    }
}
