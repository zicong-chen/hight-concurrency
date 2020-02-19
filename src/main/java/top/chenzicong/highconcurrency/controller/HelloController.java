package top.chenzicong.highconcurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chenzicong.highconcurrency.exception.MyException;
import top.chenzicong.highconcurrency.common.Response;
import top.chenzicong.highconcurrency.model.User;
import top.chenzicong.highconcurrency.redis.RedisService;
import top.chenzicong.highconcurrency.redis.TestKey;
import top.chenzicong.highconcurrency.service.MiaoshaUserService;

import java.util.List;

@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {
    private final MiaoshaUserService miaoshaUserService;
    private final RedisService redisService;

    public HelloController(MiaoshaUserService miaoshaUserService, RedisService redisService) {
        this.miaoshaUserService = miaoshaUserService;
        this.redisService = redisService;

    }

    @GetMapping("/world")
    public Response<User> hello() {
        User user = redisService.get(TestKey.testKey, "hello/world", User.class);
        if (user != null) {
            return Response.success(user);
        }
        List<User> userList = miaoshaUserService.findAll();
        if (userList.isEmpty()) {
            throw new MyException("用户获取失败");
        }
        redisService.set(TestKey.testKey, "hello/world", userList.get(0));
        return Response.success(userList.get(0));


    }

    @GetMapping("/user")
    public Response<User> validatorTest(@Validated User user) {
        log.info("user :[{}]", user);
        return Response.success(user);
    }


}
