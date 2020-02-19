package top.chenzicong.highconcurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chenzicong.highconcurrency.common.Response;
import top.chenzicong.highconcurrency.service.MiaoshaUserService;
import top.chenzicong.highconcurrency.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("login")
@Slf4j
public class LoginController {
    private final MiaoshaUserService miaoshaUserService;

    public LoginController(MiaoshaUserService miaoshaUserService) {
        this.miaoshaUserService = miaoshaUserService;
    }
    @RequestMapping("to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Response<String> doLogin(HttpServletResponse response, @Valid @RequestBody LoginVo loginVo, HttpServletRequest request) {
        log.info(loginVo.toString());
        //登录
        String header = request.getHeader("Content-Type");
        String token = miaoshaUserService.login(response, loginVo);
        return Response.success(token);

    }
}
