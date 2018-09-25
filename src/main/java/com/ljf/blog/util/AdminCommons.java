package com.ljf.blog.util;

import com.ljf.blog.pojo.Meta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by lujiafeng on 2018/9/18.
 */

/**
 * 后台公共函数
 */
@Component
public class AdminCommons {

    private static final String[] COLORS = {"jantent", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 随机产生颜色
     * @return
     */
    public static String rand_color() {
        int r = Tools.rand(0, COLORS.length-1);
        return COLORS[r];
    }

    public static boolean exist_cat(Meta category, String cats) {
        //分割字符串
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
