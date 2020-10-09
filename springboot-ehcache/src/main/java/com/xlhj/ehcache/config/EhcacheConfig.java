package com.xlhj.ehcache.config;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @Author: lcj
 * @Date: 2020/10/9 11:10
 * @Description: TODO
 * @Version: 0.0.1
 */
@Configuration
public class EhcacheConfig {

    @Bean(name = "ehcacheManager")
    public CacheManager getEhcacheManager() {
        URL url = getClass().getResource("/ehcacheConfig.xml");
        org.ehcache.config.Configuration configuration = new XmlConfiguration(url);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(configuration);
        cacheManager.init();
        return cacheManager;
    }

























}
