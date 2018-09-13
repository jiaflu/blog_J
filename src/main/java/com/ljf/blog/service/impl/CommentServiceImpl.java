package com.ljf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.mapper.CommentMapper;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.CommentExample;
import com.ljf.blog.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lujiafeng on 2018/9/13.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        //检查评论输入数据
        checkComment(comment);

        commentMapper.insert(comment);
    }

    @Override
    public PageInfo<Comment> getComments(int cid, int page, int limit) {

        return null;
    }


    private void checkComment(Comment comment) throws TipException {
        if (null == comment) {
            throw new TipException("评论对象为空");
        }
        if (StringUtils.isBlank(comment.getAuthor())) {
            comment.setAuthor("热心网友");
        }
//        if (StringUtils.isNotBlank(comments.getMail()) && !MyUtils.isEmail(comment.getMail())) {
//            throw new TipException("请输入正确的邮箱格式");
//        }
        if (StringUtils.isBlank(comment.getContent())) {
            throw new TipException("评论内容不能为空");
        }
        if (comment.getContent().length() < 5 || comment.getContent().length() > 2000) {
            throw new TipException("评论字数在5-2000个字符");
        }
        if (null == comment.getCid()) {
            throw new TipException("评论文章不能为空");
        }
    }
}
