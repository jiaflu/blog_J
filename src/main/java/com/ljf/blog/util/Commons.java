package com.ljf.blog.util;

import com.ljf.blog.constant.WebConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by lujiafeng on 2018/9/18.
 */
@Component
public class Commons {


    public String hello() {
        return "hello,world.";
    }

    /**
     * 格式化unix时间戳为日期
     * @param unixTime
     * @return
     */
    public static String fmtdate(Integer unixTime) {
        return fmtdate(unixTime, "yyyy-MM-dd");
    }

    public static String fmtdate(Integer unixTime, String pattern) {
        if (null != unixTime && StringUtils.isNotBlank(pattern)) {
            return DateKit.formatDateByUnixTime(unixTime, pattern);
        }
        return "";
    }

    public static String site_url(String sub) {
        //return site_option("site_url", "") + sub;
        return "http://localhost:8080/admin" + sub;
    }

    public static String site_option(String key, String defaultValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String str = WebConst.initConfig.get(key);
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return defaultValue;
        }
    }

    public static String artilce(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return MyUtils.mdToHtml(value);
        }
        return "";
    }
}
