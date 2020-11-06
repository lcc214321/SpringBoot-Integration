package com.xlhj.netty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcj
 * @Date: 2020/11/6 10:09
 * @Description: 启动类
 * @Version: 0.0.1
 */
@SpringBootApplication
public class NettyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
