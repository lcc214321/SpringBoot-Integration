package com.xlhj.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @ClassName UnauthorizedHandler
 * @Description 认证失败处理类
 * @Author liucaijing
 * @Date 2020/10/18 12:54
 * @Version 1.0
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8954530365317810430L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) throws IOException, ServletException {
        String message = "请求访问的资源认证失败!";
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
