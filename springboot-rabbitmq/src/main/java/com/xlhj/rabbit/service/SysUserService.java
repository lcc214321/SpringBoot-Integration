package com.xlhj.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.rabbit.entity.SysUser;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;

/**
 * @ClassName SysUserService
 * @Description 用户管理业务接口
 * @Author liucaijing
 * @Date 2020/10/12 21:59
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 创建一个交换器
     * @param name
     * @return
     */
    void createExchange(String name);

    /**
     * 创建一个队列
     * @param name
     * @return
     */
    void createQueue(String name);

    /**
     * 创建一个绑定
     * @param destination
     * @param destinationType
     * @param exchange
     * @param routingKey
     * @return
     */
    void createBinding(String destination, Binding.DestinationType destinationType, String exchange, String routingKey);

    /**
     * 发送消息
     * @param exchange
     * @param routingKey
     * @param user
     */
    void sendMessage(String exchange, String routingKey, SysUser user);

    /**
     * 接收消息
     * @param message
     */
    void recieveMessage(Message message, SysUser context);
}
