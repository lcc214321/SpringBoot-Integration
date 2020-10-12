package com.xlhj.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName CacheApplication
 * @Description 启动类
 * @Author liucaijing
 * @Date 2020/10/11 15:24
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
