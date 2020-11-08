package com.xlhj.easyexcel.controller;

import com.xlhj.easyexcel.common.ResultData;
import com.xlhj.easyexcel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ExcelController
 * @Description easyExcel控制层
 * @Author liucaijing
 * @Date 2020/11/7 22:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    /**
     * 导出Excel
     * @return
     */
    @GetMapping("/simpleWriteExcel")
    public ResultData simpleWriteExcel() {
        boolean flag = excelService.simpleWriteExcel();
        if (flag) {
            return ResultData.ok().message("写入Excel成功!");
        } else {
            return ResultData.error().message("写入Excel失败!");
        }
    }

    /**
     * 导出Excel(不导某些字段)
     * @param filedName
     * @return
     */
    @PostMapping("/excludeWriteExcel")
    public ResultData excludeWriteExcel(@RequestParam String filedName) {
        boolean flag = excelService.excludeWriteExcel(filedName);
        if (flag) {
            return ResultData.ok().message("导出Excel成功!");
        } else {
            return ResultData.error().message("导出Excel失败!");
        }
    }

    /**
     * 导出Excel(只导出某些字段)
     * @param filedName
     * @return
     */
    @PostMapping("/includeWriteExcel")
    public ResultData includeWriteExcel(@RequestParam String filedName) {
        boolean flag = excelService.includeWriteExcel(filedName);
        if (flag) {
            return ResultData.ok().message("导出Excel成功!");
        } else {
            return ResultData.error().message("导出Excel失败!");
        }
    }
}
