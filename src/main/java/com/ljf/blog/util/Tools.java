package com.ljf.blog.util;

import java.util.Random;

/**
 * Created by lujiafeng on 2018/9/14.
 */
public class Tools {
    private static final Random random =new Random();

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

    public static int rand(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }


    public static void main(String[] args) {

    }
}
