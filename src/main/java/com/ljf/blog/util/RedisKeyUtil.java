package com.ljf.blog.util;

/**
 * Created by lujiafeng on 2018/10/25.
 */
public class RedisKeyUtil {

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String tableName, String majorKey, String majorKeyValue, String column) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }

    /**
     * 自定义生成redis的key
     * 形式为：
     * 表名:主键名:主键值
     * @param tableName
     * @param majorKey
     * @param majorKeyValue
     * @return
     */
    public static String getKey(String tableName, String majorKey, String majorKeyValue) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue);
        return buffer.toString();
    }

}
