package com.xlhj.rabbit.controller;

import com.xlhj.rabbit.common.AjaxResult;
import com.xlhj.rabbit.entity.SysUser;
import com.xlhj.rabbit.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.springframework.amqp.core.Binding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/12 22:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/rabbit/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 创建交换器
     * @return
     */
    @GetMapping("/createExchange")
    @ApiOperation(value = "创建交换器")
    public AjaxResult createExchange() {
        userService.createExchange("hello-java-exchange");
        return AjaxResult.ok();
    }

    /**
     * 创建队列
     * @return
     */
    @GetMapping("/createQueue")
    @ApiOperation(value = "创建队列")
    public AjaxResult createQueue() {
        userService.createQueue("hello-java-queue");
        return AjaxResult.ok();
    }

    /**
     * 创建绑定
     * @return
     */
    @GetMapping("/createBinding")
    @ApiOperation(value = "创建绑定")
    public AjaxResult createBinding() {
        userService.createBinding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java");
        return AjaxResult.ok();
    }

    /**
     * 发送消息
     * @param id
     * @return
     */
    @GetMapping("/sendMessage/{id}")
    @ApiOperation(value = "发送消息")
    public AjaxResult sendMessage(@PathVariable Long id) {
        //SysUser user = userService.getById(id);
        SysUser user = new SysUser();
        user.setId(100L);
        user.setLoginName("zhangbaoyun");
        user.setUserName("zhangbaoyun");
        userService.sendMessage("hello-java-exchange", "hello.java", user);
        return AjaxResult.ok();
    }
}
