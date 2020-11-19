package com.xlhj.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssService
 * @Description OSS文件上传业务接口
 * @Author liucaijing
 * @Date 2020/11/19 20:35
 * @Version 1.0
 */
public interface OssService {

    /**
     * 上传头像到OSS
     * @param file
     * @return
     */
    String uploadAvatar(MultipartFile file);
}
