package com.xlhj.ehcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcj
 * @Date: 2020/10/9 11:09
 * @Description: 启动类
 * @Version: 0.0.1
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class EhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhcacheApplication.class, args);
    }
}
