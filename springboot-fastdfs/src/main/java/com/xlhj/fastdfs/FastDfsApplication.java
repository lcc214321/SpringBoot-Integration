package com.xlhj.fastdfs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcj
 * @Date: 2020/11/6 10:02
 * @Description: 启动类
 * @Version: 0.0.1
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xlhj.*.mapper")
public class FastDfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastDfsApplication.class, args);
    }
}
