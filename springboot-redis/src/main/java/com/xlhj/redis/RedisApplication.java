package com.xlhj.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcj
 * @Date: 2020/10/9 17:32
 * @Description: 启动类
 * @Version: 0.0.1
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
