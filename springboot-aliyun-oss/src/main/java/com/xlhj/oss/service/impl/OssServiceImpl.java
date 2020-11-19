package com.xlhj.oss.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xlhj.oss.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName OssServiceImpl
 * @Description OSS文件上传业务接口实现类
 * @Author liucaijing
 * @Date 2020/11/19 20:36
 * @Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    /**
     * 上传头像到OSS
     * @param file
     * @return
     */
    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            //在文件名称里面添加随机唯一的值
            String uuid = UUID.fastUUID().toString();
            fileName = uuid + fileName;
            //把文件按照日期进行分类
            LocalDate localDate = LocalDate.now();
            String datePath = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            fileName = datePath + "/" + fileName;
            //Bucket名称、上传到oss文件路径和文件名称、上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
