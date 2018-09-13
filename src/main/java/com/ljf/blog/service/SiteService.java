package com.ljf.blog.service;

import com.ljf.blog.bo.Statistics;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.Content;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/13.
 */
public interface SiteService {
    /**
     * 最新发表的文章
     * @param limit
     * @return
     */
    List<Content> recentContents(int limit);

    /**
     * 最新收到的评论
     * @param limit
     * @return
     */
    List<Comment> recentComments(int limit);

    /**
     * 获取后台统计数据
     * @return
     */
    Statistics getStatistics();

}
