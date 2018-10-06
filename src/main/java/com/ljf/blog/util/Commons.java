package com.ljf.blog.util;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.constant.WebConst;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Meta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by lujiafeng on 2018/9/18.
 */
@Component
public class Commons {

    public static String THEME = "themes/jantent";

    /**
     * 格式化unix时间为日期
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
     * 判断分页中是否有数据
     * @param pageInfo
     * @return
     */
    public static boolean is_empty(PageInfo pageInfo) {
        return (pageInfo == null) || (pageInfo.getList() == null) || (pageInfo.getList().size() == 0);
    }

    //#########################################################################

    /**
     * 在管理员页面退出登录返回到登录界面
     * @return
     */
    public static String site_login() {
        return "admin/login";
    }

    public static String site_title() {
        return site_option("site_title");
    }

    public static String site_index() {
        return "index";
    }

    public static String site_url ()  {
        return site_url("/page/1");
    }

    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    public static String site_option(String key) {
        return site_option(key, "");
    }

    public static String site_option(String key, String defaultValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String str = WebConst.initConfig.get(key);
        if (StringUtils.isNotBlank(str)) {
            //System.out.println(str);
            return str;
        } else {
            return defaultValue;
        }
    }

    /**
     * 返回文章链接地址
     * @param cid
     * @param slug
     * @return
     */
    public static String permalink(Integer cid, String slug) {
        return site_url("/article/" + (StringUtils.isNotBlank(slug) ? slug : cid.toString()));
    }

    public static String permalink(Content content) {
        return permalink(content.getCid(), content.getSlug());
    }

    /**
     * 截取字符串
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    //#########################################################################

    /**
     * 显示分类
     * @param categories
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String show_categories(String categories) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(categories)) {
            String[] arr = categories.split(",");
            //StringBuffer 和 StringBuilder 类的对象能够被多次的修改，并且不产生新的未使用对象,
            //StringBuffer线程安全
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a class=\"blog-color\" href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return show_categories("默认分类");
    }

    public static String showCategoryUrl(Meta metaDto){
        String url = "/category/"+metaDto.getName();
        return url;
    }

    /**
     * 显示标签
     * @param tags
     * @return
     * @throws UnsupportedEncodingException
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

    /**
     * 截取文章摘要
     * @param article
     * @param len 要截取文字的个数
     * @return
     */
    public static String intro(Content article, int len) {
        String value = article.getContent();
        //indexOf(str)返回字符串中str在父串中首次出现的位置
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len);
            }
            return text;
        }
    }

    /**
     * 显示文章内容，转换markdown为html
     * @param value
     * @return
     */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            //replace:target参数为被替代的内容，replacement为替代内容
            value = value.replace("<!--more-->", "\r\n");
            return  MyUtils.mdToHtml(value);
        }
        return "";
    }

    /**
     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
     * @param contents
     * @return
     */
    public static String show_thumb(Content contents) {
        if (StringUtils.isNotBlank(contents.getThumbimg())){
            return contents.getThumbimg();
        }
        int cid = contents.getCid();
        int size = cid % 25;
        size = size == 0 ? 1 : size;
        return "/user/img/rand/" + size + ".jpg";
    }


}
