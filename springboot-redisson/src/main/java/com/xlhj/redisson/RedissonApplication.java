package com.xlhj.redisson;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName RedissonApplication
 * @Description 启动类
 * @Author liucaijing
 * @Date 2020/10/11 10:29
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class RedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class, args);
    }
}
