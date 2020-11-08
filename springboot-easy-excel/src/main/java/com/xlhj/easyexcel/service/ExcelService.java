package com.xlhj.easyexcel.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ExcelService
 * @Description easyExcel处理业务接口
 * @Author liucaijing
 * @Date 2020/11/7 21:50
 * @Version 1.0
 */
public interface ExcelService {

    /**
     * 导出Excel
     * @return
     */
    boolean simpleWriteExcel();

    /**
     * 导出Excel(不导某些字段)
     * @return
     */
    boolean excludeWriteExcel(String filedName);

    /**
     * 导出Excel(只导出某些字段)
     * @return
     */
    boolean includeWriteExcel(String filedName);

    boolean simpleReadExcel();

    boolean repeatedReadExcel();

    boolean download(HttpServletResponse response);
}
