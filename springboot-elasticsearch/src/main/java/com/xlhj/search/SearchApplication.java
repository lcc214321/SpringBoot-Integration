package com.xlhj.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SearchApplication
 * @Description 启动类
 * @Author liucaijing
 * @Date 2020/10/717:26
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
