package com.ljf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ljf.blog.constant.WebConst;
import com.ljf.blog.mapper.LogMapper;
import com.ljf.blog.pojo.Log;
import com.ljf.blog.pojo.LogExample;
import com.ljf.blog.service.LogService;
import com.ljf.blog.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by lujiafeng on 2018/9/13.
 */
@Service
public class LogServicImpl implements LogService {
    @Autowired
    LogMapper logMapper;

    @Override
    public void add(Log log) {
        logMapper.insert(log);
    }

    @Override
    public void add(String action, String data, String ip, Integer authorId) {
        Log log = new Log();
        log.setAction(action);
        log.setData(data);
        log.setIp(ip);
        log.setAuthorId(authorId);
        log.setCreated(DateKit.getCurrentUnixTime());
        logMapper.insert(log);
    }

    @Override
    public List<Log> getLogs(int page, int limit) {
        if (page <= 0) {
            page = 1;
        }
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }
        LogExample example = new LogExample();
        example.setOrderByClause("id desc");
        //最新的数据在最后面,页数的起始页为1
        PageHelper.startPage(page, limit);
        List<Log> logs = logMapper.selectByExample(example);
        return logs;

    }

}
