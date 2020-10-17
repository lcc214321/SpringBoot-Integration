package com.xlhj.shiro.controller;

import com.xlhj.shiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(ModelMap model) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        model.put("user", user);
        return "index";
    }

    @GetMapping("/system/main")
    public String main(ModelMap modelMap) {
        modelMap.put("welcome", "欢迎光临!");
        return "main";
    }
}
