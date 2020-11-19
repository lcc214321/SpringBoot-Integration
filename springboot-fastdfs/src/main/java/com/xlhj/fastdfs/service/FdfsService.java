package com.xlhj.fastdfs.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FdfsService
 * @Description Fdfs业务接口
 * @Author liucaijing
 * @Date 2020/11/18 20:33
 * @Version 1.0
 */
public interface FdfsService {

    String upload(MultipartFile file, String fileExtName) throws Exception;
}
