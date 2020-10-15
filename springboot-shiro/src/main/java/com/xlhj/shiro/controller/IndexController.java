package com.xlhj.shiro.controller;

import com.xlhj.shiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/15 21:21
 * @Version 1.0
 */
@Controller
public class IndexController {

    /**
     * 首页
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("currentUser", currentUser);
        return "index.html";
    }
}
