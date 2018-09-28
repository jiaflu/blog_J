package com.ljf.blog.controller.admin;

import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.bo.Statistics;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Log;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.LogService;
import com.ljf.blog.service.SiteService;
import com.ljf.blog.service.UserService;
import com.ljf.blog.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lujiafeng on 2018/9/12.
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @Autowired
    SiteService siteService;
    @Autowired
    Commons commons;

    /**
     * 管理中心起始页
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        List<Content> contents = siteService.recentContents(5);
        List<Comment> comments = siteService.recentComments(5);
        Statistics statistics = siteService.getStatistics();

        //取最新的5条日志
        List<Log> logs = logService.getLogs(1, 5);
        request.setAttribute("articles", contents);
        request.setAttribute("comments", comments);
        request.setAttribute("statistics", statistics);
        request.setAttribute("logs", logs);
        request.setAttribute("commons", commons);
        return "admin/index";
    }

//    @GetMapping("profile")
//    public String profile() {
//        return "admin/profile";
//    }
//
//    @GetMapping("logout")
//    public String logout() {
//        return "admin/logout";
//    }

    @PostMapping("/profile")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)  //事务
    public RestResponse saveProfile(String screenName, String email, HttpServletRequest request, HttpSession session) {

        return RestResponse.ok();
    }
}
