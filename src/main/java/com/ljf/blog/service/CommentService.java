package com.ljf.blog.service;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.pojo.Comment;

/**
 * Created by lujiafeng on 2018/9/11.
 */
public interface CommentService {

    void insertComment(Comment comment);

    PageInfo<Comment> getComments(int cid, int page, int limit);


}
