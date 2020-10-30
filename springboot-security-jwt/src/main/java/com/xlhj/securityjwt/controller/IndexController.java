package com.xlhj.securityjwt.controller;

import com.xlhj.securityjwt.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: lcj
 * @Date: 2020/10/29 16:09
 * @Description: TODO
 * @Version: 0.0.1
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/system/user")
    public String userList() {
        return "user";
    }

    @GetMapping("/system/role")
    public String roleList() {
        return "role";
    }
}
