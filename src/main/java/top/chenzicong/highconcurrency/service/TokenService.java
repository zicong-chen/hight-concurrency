package top.chenzicong.highconcurrency.service;

import top.chenzicong.highconcurrency.common.Response;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    Response createToken();

    void Check(HttpServletRequest request);
}
