package com.xlhj.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName MybatisPlusApplication
 * @Description 启动类
 * @Author liucaijing
 * @Date 2020/10/19:29
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
