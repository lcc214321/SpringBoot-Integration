package com.xlhj.rabbit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.rabbit.entity.SysUser;
import com.xlhj.rabbit.mapper.SysUserMapper;
import com.xlhj.rabbit.service.SysUserService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户管理业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/12 22:00
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void createExchange(String name) {
        DirectExchange directExchange = new DirectExchange(name, true, false);
        amqpAdmin.declareExchange(directExchange);
    }

    @Override
    public void createQueue(String name) {
        Queue queue = new Queue(name, true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Override
    public void createBinding(String destination, Binding.DestinationType destinationType, String exchange, String routingKey) {
        Binding binding = new Binding(destination, destinationType, exchange, routingKey, null);
        amqpAdmin.declareBinding(binding);
    }

    @Override
    public void sendMessage(String exchange, String routingKey, SysUser user) {
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", user);
    }

    @Override
    @RabbitListener(queues = {"hello-java-queue"})
    public void recieveMessage(Message message, SysUser context) {
        byte[] body = message.getBody();
        MessageProperties properties = message.getMessageProperties();
        System.out.println("接收到消息...内容：" + message + "===>内容：" + context);
    }
}
