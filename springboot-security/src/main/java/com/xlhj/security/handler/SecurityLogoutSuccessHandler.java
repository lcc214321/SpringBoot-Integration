package com.xlhj.security.handler;

import com.alibaba.fastjson.JSON;
import com.xlhj.security.common.AjaxResult;
import com.xlhj.security.entity.LoginUser;
import com.xlhj.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LogoutSuccessHandler
 * @Description 自定义退出处理类
 * @Author liucaijing
 * @Date 2020/10/18 13:06
 * @Version 1.0
 */
@Configuration
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser != null) {
            String username = loginUser.getUsername();
            tokenService.deleteLoginUser(loginUser.getToken());
        }
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("退出成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
