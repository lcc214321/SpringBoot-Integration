package com.xlhj.shiro.util;

/**
 * @Author: lcj
 * @Date: 2020/10/13 9:55
 * @Description: String工具类
 * @Version: 0.0.1
 */
public class StringUtils {

    /**
     * 判断一个对象是否为非空
     * @param object
     * @return
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断一个对象是否为空
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
}
