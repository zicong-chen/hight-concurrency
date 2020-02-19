package top.chenzicong.highconcurrency.service;

import top.chenzicong.highconcurrency.model.MiaoshaUser;
import top.chenzicong.highconcurrency.model.User;
import top.chenzicong.highconcurrency.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MiaoshaUserService {
    String COOKIE_NAME_TOKEN = "token";

    List<User> findAll();

    String login(HttpServletResponse response, LoginVo loginVo);

    MiaoshaUser getByToken(HttpServletResponse response, String token);
}
