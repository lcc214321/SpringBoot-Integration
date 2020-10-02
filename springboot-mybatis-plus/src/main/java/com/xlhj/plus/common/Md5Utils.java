package com.xlhj.plus.common;

import java.security.MessageDigest;

/**
 * @ClassName Md5Utils
 * @Description 安全密码相关工具类
 * @Author liucaijing
 * @Date 2020/10/19:19
 * @Version 1.0
 */
public class Md5Utils {

    public static byte[] md5(String str) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(str.getBytes("UTF-8"));
            byte[] messageDigest = digest.digest();
            return messageDigest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer(hash.length * 2);
        int i;
        for (int j = 0; j < hash.length; j++) {
            if ((hash[j] & 0xff) < 0x10) {
                sb.append("0");
            }
            sb.append(Long.toString(hash[j] & 0xff, 16));
        }
        return sb.toString();
    }
}
