package com.xlhj.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.xlhj.easyexcel.entity.SysUser;
import com.xlhj.easyexcel.listener.UserDataListener;
import com.xlhj.easyexcel.mapper.SysUserMapper;
import com.xlhj.easyexcel.service.ExcelService;
import com.xlhj.easyexcel.util.ExcelFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ExcelServiceImpl
 * @Description easyExcel处理业务接口实现类
 * @Author liucaijing
 * @Date 2020/11/7 21:50
 * @Version 1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public boolean simpleWriteExcel() {
        String fileName = ExcelFileUtil.getPath() + "用户信息" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, SysUser.class).sheet("用户信息").doWrite(data());
        fileName = ExcelFileUtil.getPath() + "用户信息" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, SysUser.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("用户信息").build();
            excelWriter.write(data(), writeSheet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        return false;
    }

    /**
     * 导出Excel(不导某些字段)
     * @return
     */
    @Override
    public boolean excludeWriteExcel(String filedName) {
        String fileName = ExcelFileUtil.getPath() + "excludeWriteExcel" + System.currentTimeMillis() + ".xlsx";
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        try {
            excludeColumnFiledNames.add(filedName);
            EasyExcel.write(fileName, SysUser.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("用户信息").doWrite(data());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 导出Excel(只导出某些字段)
     * @return
     */
    @Override
    public boolean includeWriteExcel(String filedName) {
        String fileName = ExcelFileUtil.getPath() + "includeWriteExcel" + System.currentTimeMillis() + ".xlsx";
        Set<String> includeColumnFiledNames = new HashSet<String>();
        try {
            includeColumnFiledNames.add(filedName);
            EasyExcel.write(fileName, SysUser.class).includeColumnFiledNames(includeColumnFiledNames).sheet("用户信息").doWrite(data());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取单个sheet
     * @return
     */
    @Override
    public boolean simpleReadExcel() {
        String fileName = ExcelFileUtil.getPath() + "simpleReadExcel" + File.separator + "simpleReadExcel.xlsx";
        EasyExcel.read(fileName, SysUser.class, new UserDataListener()).sheet().doRead();
        fileName = ExcelFileUtil.getPath() + "simpleReadExcel" + File.separator + "simpleReadExcel.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, SysUser.class, new UserDataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelReader != null) {
                excelReader.finish();
            }
        }
        return false;
    }

    /**
     * 读取多个sheet
     * @return
     */
    @Override
    public boolean repeatedReadExcel() {
        String fileName = ExcelFileUtil.getPath() + "repeatedReadExcel" + File.separator + "repeatedReadExcel.xlsx";
        EasyExcel.read(fileName, SysUser.class, new UserDataListener()).doReadAll();
        fileName = ExcelFileUtil.getPath() + "repeatedReadExcel" + File.separator + "repeatedReadExcel.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName).build();
            ReadSheet readSheet1 = EasyExcel.readSheet(0).head(SysUser.class).registerReadListener(new UserDataListener()).build();
            ReadSheet readSheet2 = EasyExcel.readSheet(1).head(SysUser.class).registerReadListener(new UserDataListener()).build();
            excelReader.read(readSheet1, readSheet2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelReader != null) {
                excelReader.finish();
            }
        }
        return false;
    }

    /**
     * 获取用户数据
     * @return
     */
    private List<SysUser> data() {
        List<SysUser> list = userMapper.selectList(null);
        return list;
    }





















}
