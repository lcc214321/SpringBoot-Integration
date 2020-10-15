package com.xlhj.shiro.common;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName Md5Utils
 * @Description 安全密码相关工具类
 * @Author liucaijing
 * @Date 2020/10/19:19
 * @Version 1.0
 */
public class Md5Utils {

    /**
     * shiro密码加密
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin", "123456", "111111"));
    }
}
