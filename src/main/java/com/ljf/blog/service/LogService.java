package com.ljf.blog.service;

import com.ljf.blog.pojo.Log;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/11.
 */
public interface LogService {
    /**
     * 插入日志
     * @param log
     */
    void add(Log log);

    /**
     * 插入日志
     * @param action
     * @param data
     * @param ip
     * @param author_id
     */
    void add(String action, String data, String ip, Integer author_id);

    /**
     * 获取日志分页
     * @param page
     * @param limit
     * @return
     */
    List<Log> getLogs(int page, int limit);
}
