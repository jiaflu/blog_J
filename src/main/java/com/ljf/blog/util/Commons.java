package com.ljf.blog.util;

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
}
