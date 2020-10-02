package com.xlhj.swagger.controller;

import com.xlhj.swagger.common.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SwaggerController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/214:49
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/swagger")
public class SwaggerController {

    @GetMapping("get/{id}")
    @ApiOperation(value = "获取数据")
    public AjaxResult getData(@PathVariable String id) {
        System.out.println("springboot2.3整合swagger3.0");
        return AjaxResult.ok();
    }
}
