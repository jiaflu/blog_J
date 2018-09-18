package com.ljf.blog.util;

/**
 * Created by lujiafeng on 2018/9/14.
 */
public class Tools {

    /**
     * 判断是否为整型数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
