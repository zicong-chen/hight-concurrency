package top.chenzicong.highconcurrency.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.chenzicong.highconcurrency.annotation.ApiIdempotent;
import top.chenzicong.highconcurrency.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ApiIdempotentIntercepter implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ApiIdempotent annotation = handlerMethod.getMethod().getAnnotation(ApiIdempotent.class);
        if (annotation != null) {
            check(request);
        }
        return true;
    }

    private void check(HttpServletRequest request) {
        tokenService.Check(request);
    }
}
