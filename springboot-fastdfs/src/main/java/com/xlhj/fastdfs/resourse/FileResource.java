package com.xlhj.fastdfs.resourse;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName FileResourse
 * @Description 读取配置文件类
 * @Author liucaijing
 * @Date 2020/11/18 21:04
 * @Version 1.0
 */
@Data
@Component
@PropertySource("classpath:file.properties")
@ConfigurationProperties(prefix = "file")
public class FileResource {

    private String host;
}
