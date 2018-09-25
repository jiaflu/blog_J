package com.ljf.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.dto.LogActions;
import com.ljf.blog.exception.ExceptionHelper;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.LogService;
import com.ljf.blog.util.Commons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lujiafeng on 2018/9/24.
 */


@Controller
@RequestMapping("admin/page")
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    ContentService contentService;
    @Autowired
    LogService logService;
    @Autowired
    Commons commons;

    @GetMapping("")
    public String index(HttpServletRequest request) {
        PageInfo<Content> contentPageInfo = contentService.getContents(1, 999);
        request.setAttribute("articles", contentPageInfo);
        request.setAttribute("commons", commons);
        return "admin/page_list";
    }

    @GetMapping("new")
    public String newPage(HttpServletRequest request) {
        return "admin/page_edit";
    }



    @RequestMapping("delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse delete(int cid, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("login_user");
        try {
            contentService.delete(cid);
            logService.add(LogActions.DEL_PAGE.getAction(), cid + "", request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            String msg = "页面删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }
}
