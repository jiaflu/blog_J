package com.ljf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ljf.blog.bo.Statistics;
import com.ljf.blog.mapper.AttachMapper;
import com.ljf.blog.mapper.CommentMapper;
import com.ljf.blog.mapper.ContentMapper;
import com.ljf.blog.mapper.MetaMapper;
import com.ljf.blog.pojo.*;
import com.ljf.blog.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/13.
 */
@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AttachMapper attachMapper;
    @Autowired
    MetaMapper metaMapper;


    @Override
    public List<Content> recentContents(int limit) {
        if (limit <0 || limit > 10) {
            limit = 10;
        }
        ContentExample example = new ContentExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(1, limit);
        List<Content> contents = contentMapper.selectByExample(example);
        return contents;
    }

    @Override
    public List<Comment> recentComments(int limit) {
        if (limit <0 || limit > 10) {
            limit = 10;
        }
        CommentExample example = new CommentExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(1, limit);
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments;
    }

    @Override
    public Statistics getStatistics() {
        Statistics statistics = new Statistics();

        ContentExample contentExample = new ContentExample();
        Long articles = contentMapper.countByExample(contentExample);

        CommentExample commentExample = new CommentExample();
        Long comments = commentMapper.countByExample(commentExample);

        AttachExample attachExample = new AttachExample();
        Long attachs = attachMapper.countByExample(attachExample);

        MetaExample metaExample = new MetaExample();
        //metaVoExample.createCriteria().andTypeEqualTo(Types.LINK.getType());
        Long links = metaMapper.countByExample(metaExample);

        statistics.setArticles(articles);
        statistics.setComments(comments);
        statistics.setAttachs(attachs);
        statistics.setLinks(links);

        return statistics;
    }
}
