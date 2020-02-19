package top.chenzicong.highconcurrency.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.chenzicong.highconcurrency.exception.MyException;
import top.chenzicong.highconcurrency.common.Response;
import top.chenzicong.highconcurrency.common.StatusCode;
import top.chenzicong.highconcurrency.redis.MiaoshaUserKey;
import top.chenzicong.highconcurrency.redis.RedisService;
import top.chenzicong.highconcurrency.service.TokenService;
import top.chenzicong.highconcurrency.utils.UUIDUtil;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 创建一个token 并保存在redis中。
     *
     * @return
     */
    @Override
    public Response createToken() {
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token, token, token);
        return Response.success(token);
    }

    @Override
    public void Check(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                //完全没有token, 算作重复操作
                throw new MyException(StatusCode.REPETITIVE_OPERATION);
            }
        }
        //token不符合要求
        if (!redisService.exists(MiaoshaUserKey.token, token)) {
            throw new MyException(StatusCode.REPETITIVE_OPERATION);
        }


        Long del = redisService.del(MiaoshaUserKey.token, token);

        log.info("----------del :" + del.toString());
        //redis的删除是一个原子性的操作， 我们可以判断是否删除成功来判断是否是重复操作
        if (del == 0) {
            throw new MyException(StatusCode.REPETITIVE_OPERATION);
        }
    }
}
