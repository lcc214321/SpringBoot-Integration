package com.xlhj.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/25 19:33
 * @Version 1.0
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }
}
