package com.xlhj.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @ClassName RedissonConfig
 * @Description redisson配置类
 * @Author liucaijing
 * @Date 2020/10/11 10:32
 * @Version 1.0
 */
@Configuration
public class RedissonConfig {

    /**
     * 初始化Redisson客户端
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.108.11:6379");
        return Redisson.create(config);
    }


























}
