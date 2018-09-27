package com.ljf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.mapper.CommentMapper;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.CommentExample;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.service.CommentService;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.util.DateKit;
import com.ljf.blog.util.MyUtils;
import net.sf.jsqlparser.expression.StringValue;
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
    @Autowired
    ContentService contentService;

    @Override
    public void insertComment(Comment comment) {
        //检查评论输入数据
        checkComment(comment);
        Content content = contentService.getContent(String.valueOf(comment.getCid()));
        if (null == content) {
            throw new TipException("文章不存在");
        }
        comment.setOwnerId(content.getAuthorId());
        comment.setCreated(DateKit.getCurrentUnixTime());
        commentMapper.insertSelective(comment);

        Content temp = new Content();
        temp.setCid(content.getCid());
        temp.setCommentsNum(content.getCommentsNum() + 1);
        contentService.update(temp);
    }

    @Override
    public PageInfo<Comment> getCommentsWithPage(CommentExample commentExample, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return new PageInfo<>(comments);
    }

    @Override
    public PageInfo<Comment> getComments(Integer cid, int page, int limit) {
        if (null != cid) {
            PageHelper.startPage(1, 5);
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andCidEqualTo(cid);
            commentExample.setOrderByClause("coid desc");  //降序，即最新的排在最前面
            List<Comment> comments = commentMapper.selectByExample(commentExample);
            PageInfo<Comment> pageInfo = new PageInfo<>(comments);
            /*

             */
            return pageInfo;
        }
        return null;
    }

    @Override
    public Comment getCommentById(Integer coid) {
        if (null != coid) {
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andCoidEqualTo(coid);
            List<Comment> comments = commentMapper.selectByExample(commentExample);
            if (comments.isEmpty()) {
                return null;
            } else {
                return comments.get(0);
            }
        }
        return null;
    }

    @Override
    public void delete(Integer coid, Integer cid) {
        if (null == cid) {
            throw new TipException("外键为空");
        }
        if (null != coid) {
            commentMapper.deleteByPrimaryKey(coid);
            Content content = contentService.getContent(String.valueOf(cid));
            if (null != content && content.getCommentsNum() > 0) {
                Content temp = new Content();
                temp.setCid(cid);
                temp.setCommentsNum(content.getCommentsNum() - 1);
                contentService.update(temp);
            }
        }
    }

    @Override
    public void update(Comment comment) {

    }



    private void checkComment(Comment comment) throws TipException {
        if (null == comment) {
            throw new TipException("评论对象为空");
        }
        if (StringUtils.isBlank(comment.getAuthor())) {
            comment.setAuthor("热心网友");
        }
        if (StringUtils.isNotBlank(comment.getMail()) && !MyUtils.isEmail(comment.getMail())) {
            throw new TipException("请输入正确的邮箱格式");
        }
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
