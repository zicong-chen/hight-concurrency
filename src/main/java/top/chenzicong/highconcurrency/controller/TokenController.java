package top.chenzicong.highconcurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chenzicong.highconcurrency.common.Response;
import top.chenzicong.highconcurrency.model.User;
import top.chenzicong.highconcurrency.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public Response token() {
        return tokenService.createToken();
    }

}
