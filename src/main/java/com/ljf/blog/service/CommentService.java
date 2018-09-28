package com.ljf.blog.service;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.CommentExample;

/**
 * Created by lujiafeng on 2018/9/11.
 */
public interface CommentService {

    /**
     * 插入评论
     * @param comment
     */
    void add(Comment comment);


    /**
     * 根据主键查询评论
     * @param coid
     * @return
     */
    Comment getComment(Integer coid);


    /**
     * 获取对应文章下面的评论
     * @param cid
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Comment> getComments(Integer cid, Integer page, Integer limit);

    /**
     * 获取评论
     * @param commentExample
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Comment> getComments(CommentExample commentExample, Integer page, Integer limit);



    /**
     * 删除评论
     * @param coid
     * @param cid
     */
    void delete(Integer coid, Integer cid);

    /**
     * 更新评论
     * @param comment
     */
    void update(Comment comment);
}
