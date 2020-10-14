package com.xlhj.shiro.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.entity.SysMenu;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Struct;
import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/14 8:46
 * @Description: 首页
 * @Version: 0.0.1
 */
@Controller
public class SysIndexController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 系统首页
     * @param modelMap
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap modelMap) {
        //获取用户信息
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        modelMap.put("menus", menus);
        modelMap.put("user", user);
        return "index";
    }
}
