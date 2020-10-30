package com.xlhj.securityjwt.controller;

import com.xlhj.securityjwt.common.ResultData;
import com.xlhj.securityjwt.service.JwtAuthService;
import com.xlhj.securityjwt.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcj
 * @Date: 2020/10/23 17:42
 * @Description: TODO
 * @Version: 0.0.1
 */
@RestController
public class LoginController {

    @Autowired
    private JwtAuthService jwtAuthService;

    /**
     * 登录
     * @param loginUser
     * @return
     */
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginUser loginUser) {
        String token = jwtAuthService.login(loginUser.getUsername(), loginUser.getPassword());
        return ResultData.ok().data("token", token);
    }
}
