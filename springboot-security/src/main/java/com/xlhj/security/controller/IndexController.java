package com.xlhj.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/25 19:33
 * @Version 1.0
 */
@Controller
public class IndexController {

    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "/index";
    }
}
