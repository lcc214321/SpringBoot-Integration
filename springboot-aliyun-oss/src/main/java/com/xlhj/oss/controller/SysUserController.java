package com.xlhj.oss.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.oss.common.ResultData;
import com.xlhj.oss.service.OssService;
import com.xlhj.oss.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/11/19 20:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private OssService ossService;
    @Autowired
    private SysUserService userService;

    /**
     * 上传头像到OSS
     * @param userId
     * @param file
     * @return
     */
    @ApiOperation("上传头像到OSS")
    @PostMapping("/uploadAvatar")
    public ResultData uploadAvatar(Long userId, MultipartFile file) {
        String avatarPath = "";
        if (file != null) {
            avatarPath = ossService.uploadAvatar(file);
        } else {
            return ResultData.error().message("文件不能为空!");
        }
        if (StrUtil.isNotBlank(avatarPath)) {
            int num = userService.updateAvatarByUserId(userId, avatarPath);
            if (num > 0) {
                return ResultData.ok().message("保存头像路径成功!");
            } else {
                return ResultData.error().message("保存头像路径失败!");
            }
        } else {
            return ResultData.error().message("上传头像失败!");
        }
    }
}
