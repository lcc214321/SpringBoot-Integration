package com.xlhj.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcj
 * @Date: 2020/10/10 17:49
 * @Description: 启动类
 * @Version: 0.0.1
 */
@SpringBootApplication
@MapperScan(value = "com.xlhj.*.mapper")
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
