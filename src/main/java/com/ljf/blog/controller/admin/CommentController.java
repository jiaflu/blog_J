package com.ljf.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.exception.ExceptionHelper;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.CommentExample;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.CommentService;
import com.ljf.blog.util.Commons;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lujiafeng on 2018/9/24.
 */

@Controller
@RequestMapping("admin/comments")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;
    @Autowired
    Commons commons;

    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("login_user");
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andAuthorIdEqualTo(user.getUid());
        commentExample.setOrderByClause("coid desc");
        PageInfo<Comment> commentPageInfo = commentService.getCommentsWithPage(commentExample, page, limit);
        request.setAttribute("comments", commentPageInfo);
        request.setAttribute("commons", commons);
        return "admin/comment_list";
    }

    @PostMapping("delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse delete(Integer coid) {
        try {
            Comment comment = commentService.getCommentById(coid);
            if (null == comment) {
                return RestResponse.fail("不存在该评论");
            }
            commentService.delete(coid, comment.getCid());
        } catch (Exception e) {
            String msg = "评论删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }

    /***
     * 更新评论状态
     * @param coid
     * @param status
     * @return
     */
    @PostMapping("status")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse updateStatus(Integer coid, String status) {
        try {
            Comment comment = commentService.getCommentById(coid);
            comment.setCoid(coid);
            comment.setStatus(status);
            commentService.update(comment);
        } catch (Exception e) {
            String msg = "更新评论状态失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }

    @PostMapping("")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse reply(Integer coid, String commentContent, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("login_user");
        if (null == coid || StringUtils.isBlank(commentContent)) {
            return RestResponse.fail("请输入完整后评论");
        }
        if (commentContent.length() > 2000) {
            return RestResponse.fail("请输入2000个字符以内的评论");
        }
        Comment temp = commentService.getCommentById(coid);
        if (null == temp) {
            return RestResponse.fail("不存在该评论");
        }
        /*
        MyUtil符号表情过滤
         */
        Comment comment = new Comment();
        comment.setAuthor(user.getUsername());
        comment.setAuthorId(user.getUid());
        comment.setCid(temp.getCid());
        comment.setIp(request.getRemoteAddr());
        comment.setUrl(user.getHomeUrl());
        comment.setContent(commentContent);
        comment.setMail(user.getEmail());
        comment.setParent(coid);

        try {
            commentService.insertComment(comment);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = "回复失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
    }
}
