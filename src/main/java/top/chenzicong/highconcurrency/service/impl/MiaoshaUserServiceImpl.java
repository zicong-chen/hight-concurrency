package top.chenzicong.highconcurrency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.chenzicong.highconcurrency.exception.MyException;
import top.chenzicong.highconcurrency.common.StatusCode;
import top.chenzicong.highconcurrency.dao.UserMapper;
import top.chenzicong.highconcurrency.model.MiaoshaUser;
import top.chenzicong.highconcurrency.model.User;
import top.chenzicong.highconcurrency.redis.MiaoshaUserKey;
import top.chenzicong.highconcurrency.redis.RedisService;
import top.chenzicong.highconcurrency.service.MiaoshaUserService;
import top.chenzicong.highconcurrency.utils.JwtTokenUtil;
import top.chenzicong.highconcurrency.utils.MD5Util;
import top.chenzicong.highconcurrency.utils.UUIDUtil;
import top.chenzicong.highconcurrency.vo.LoginVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {
    private final UserMapper userMapper;
    private final RedisService redisService;
    private final UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public MiaoshaUserServiceImpl(UserMapper userMapper, RedisService redisService, UserDetailsService userDetailsService) {
        this.userMapper = userMapper;
        this.redisService = redisService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new MyException(StatusCode.FAIL);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        MiaoshaUser miaoshaUser = userMapper.getById(Long.parseLong(mobile));
        if (miaoshaUser == null) {
            throw new MyException(StatusCode.MOBILE_NOT_EXIT);
        }
        String dbPass = miaoshaUser.getPassword();
        String salt = miaoshaUser.getSalt();
        String calc = MD5Util.formPassToDBPass(password, salt);
        if (!calc.equals(dbPass)) {
            throw new MyException(StatusCode.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response, token, miaoshaUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getMobile());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        String token2 = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token: " +token2);

        return token2;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);


    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

}
