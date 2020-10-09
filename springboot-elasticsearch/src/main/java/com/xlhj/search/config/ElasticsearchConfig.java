package com.xlhj.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName elasticsearchConfig
 * @Description elasticsearch配置类
 * @Author liucaijing
 * @Date 2020/10/717:50
 * @Version 1.0
 */
@Configuration
public class ElasticsearchConfig {

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient restClient() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.108.11", 9200, "http")));
        return client;
    }
}
