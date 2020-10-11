package com.xlhj.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName RabbitApplication
 * @Description 启动类
 * @Author liucaijing
 * @Date 2020/10/11 12:57
 * @Version 1.0
 */
@SpringBootApplication
@EnableRabbit
public class RabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
    }
}
