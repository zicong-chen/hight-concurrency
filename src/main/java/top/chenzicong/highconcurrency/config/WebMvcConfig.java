package top.chenzicong.highconcurrency.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.chenzicong.highconcurrency.interceptor.ApiIdempotentIntercepter;
import top.chenzicong.highconcurrency.interceptor.LoginInterceptor;
import top.chenzicong.highconcurrency.interceptor.UploadFileIntercepter;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final UserArgumentResolver userArgumentResolver;
    private final ApiIdempotentIntercepter apiIdempotentIntercepter;
    private final UploadFileIntercepter uploadFileIntercepter;

    public WebMvcConfig(LoginInterceptor loginInterceptor, UserArgumentResolver userArgumentResolver, ApiIdempotentIntercepter apiIdempotentIntercepter, UploadFileIntercepter uploadFileIntercepter) {
        this.loginInterceptor = loginInterceptor;
        this.userArgumentResolver = userArgumentResolver;
        this.apiIdempotentIntercepter = apiIdempotentIntercepter;
        this.uploadFileIntercepter = uploadFileIntercepter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors:---------");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        registry.addInterceptor(apiIdempotentIntercepter).addPathPatterns("/**");
        registry.addInterceptor(uploadFileIntercepter).addPathPatterns("/upload/uploadFile");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    //但是使用此方法配置之后再使用自定义拦截器时跨域相关配置就会失效。
//原因是请求经过的先后顺序问题，当请求到来时会先进入拦截器中，而不是进入Mapping映射中，,
    //如果没有进入mapping 映射中, 我们这个方法的配置其实不会生效
// 所以返回的头信息中并没有配置的跨域信息。浏览器就会报跨域异常。
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("addCorsMappings:----------");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600)
                .allowCredentials(true);
    }


}
