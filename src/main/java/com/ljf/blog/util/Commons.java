package com.ljf.blog.util;

import com.ljf.blog.constant.WebConst;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Meta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    /**
     *
     * 网站标题
     * @return
     */
    public static String site_title() {
        return site_option("site_title");
    }

    public static String site_url() {
        return site_url("/page/1");
    }

    public static String site_url(String sub) {
        //return site_option("site_url", "") + sub;
        return "http://localhost:8080/admin" + sub;
    }

    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     * @param key
     * @param defaultValue
     * @return
     */
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

    /**
     * 显示文章缩略图，顺序为：若文章有缩略图则获取，若无则随机获取
     * @param content
     * @return
     */
    public static String show_thumb(Content content) {
        if (StringUtils.isNotBlank(content.getThumbimg())){
            return content.getThumbimg();
        }
        int cid = content.getCid();
        int size = cid % 25;
        size = size == 0 ? 1 : size;
        return "/user/img/rand/" + size + ".jpg";
    }


    /**
     * 返回文章链接地址
     * @param content
     * @return
     */
    public static String permalink(Content content) {
        return permalink(content.getCid(), content.getSlug());
    }
    public static String permalink(Integer cid, String slug) {
        return site_url("/article/" + (StringUtils.isNotBlank(slug) ? slug : cid.toString()));
    }

    /**
     * 截取文章摘要
     * @param article (文章)
     * @param len (要截取文字的个数)
     * @return
     */
    public static String intro(Content article, int len) {
        String value = article.getContent();
        //indexOf(String str) 返回指定字符串在此字符串中第一次出现处的索引
//        int pos = value.indexOf("<!--more-->");
//        if (pos != -1) {
//            String html = value.substring(0, pos);
//            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
//        } else {
//            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
//            if (text.length() > len) {
//                return text.substring(0, len);
//            }
//            return text;
//        }
        String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
        if (text.length() > len) {
            return text.substring(0, len);
        }
        return text;
    }

    public static String showCategoryUrl(Meta meta){
        String url = "/category/"+meta.getName();
        return url;
    }

    /**
     * 显示文章内容，转换markdown为html
     *
     * @param value
     * @return
     */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return MyUtils.mdToHtml(value);
        }
        return "";
    }


    /**
     * 显示分类
     *
     * @param categories
     * @return
     */
    public static String show_categories(String categories) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(categories)) {
            String[] arr = categories.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a class=\"blog-color\" href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return show_categories("默认分类");
    }

    /**
     * 显示标签
     *
     * @param tags
     * @return
     */
    public static String show_tags(String tags) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(tags)) {
            String[] arr = tags.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a href=\"/tag/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return "";
    }
}
