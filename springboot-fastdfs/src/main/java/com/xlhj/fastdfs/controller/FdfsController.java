package com.xlhj.fastdfs.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.fastdfs.common.ResultData;
import com.xlhj.fastdfs.resourse.FileResource;
import com.xlhj.fastdfs.service.FdfsService;
import com.xlhj.fastdfs.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FdfsController
 * @Description Fdfs控制器
 * @Author liucaijing
 * @Date 2020/11/18 20:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/fdfs")
public class FdfsController {

    @Autowired
    private FdfsService fdfsService;
    @Autowired
    private FileResource fileResource;
    @Autowired
    private SysUserService userService;

    /**
     * 上传用户头像
     * @param userId
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadAvatar")
    public ResultData uploadAvatar(Long userId, MultipartFile file) throws Exception {
        String path = "";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            if (StrUtil.isNotEmpty(fileName)) {
                String fileNameArr[] = fileName.split("\\.");
                String suffix = fileNameArr[fileNameArr.length - 1];
                if (!suffix.equalsIgnoreCase("png") && !suffix.equalsIgnoreCase("jpg") && !suffix.equalsIgnoreCase("jpeg")) {
                    return ResultData.error().message("图片格式不正确!");
                }
                path = fdfsService.upload(file, suffix);
            }
        } else {
            return ResultData.error().message("文件不能为空!");
        }
        if (StrUtil.isNotBlank(path)) {
            String faceUrl = fileResource.getHost() + path;
            int num = userService.updateAvatarById(userId, faceUrl);
            if (num > 0) {
                return ResultData.ok().message("用户头像保存数据库成功!");
            } else {
                return ResultData.error().message("用户头像保存数据库失败!");
            }
        } else {
            return ResultData.error().message("上传图片失败!");
        }
    }
}
