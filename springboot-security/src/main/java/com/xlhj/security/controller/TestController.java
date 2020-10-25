package com.xlhj.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/25 16:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("update")
    @Secured({"ROLE_sale", "ROLE_admin"})
    public String update() {
        return "hello update";
    }

    @GetMapping("add")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String add() {
        return "hello add";
    }
}
