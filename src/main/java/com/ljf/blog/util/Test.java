package com.ljf.blog.util;

/**
 * Created by lujiafeng on 2018/9/13.
 */
public class Test {

    public static void main(String[] args) {
        String s = "dasdasdas";
        new Test().changeStr(s);
        System.out.println(s);
    }

    public void changeStr(String str) {
        str = "123qqq";
    }
}
