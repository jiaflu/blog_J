package com.ljf.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.dto.LogActions;
import com.ljf.blog.dto.Types;
import com.ljf.blog.exception.ExceptionHelper;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Meta;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.LogService;
import com.ljf.blog.service.MetaService;
import com.ljf.blog.util.AdminCommons;
import com.ljf.blog.util.Commons;
import org.apache.commons.lang3.StringUtils;
import org.json.HTTP;
import org.slf4j.Logger;
import com.ljf.blog.exception.TipException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lujiafeng on 2018/9/16.
 */

@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ContentService contentService;
    @Autowired
    LogService logService;
    @Autowired
    MetaService metaService;

    @Autowired
    Commons commons;

    @Autowired
    AdminCommons adminCommons;

    /**
     * 文章列表页面
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @GetMapping("")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit,
                        HttpServletRequest request) {
        //注：此处查询得出的文章为已发布的文章
        PageInfo<Content> contentPageInfo = contentService.getArticles(page, limit);
        System.out.println(contentPageInfo.getSize());
        request.setAttribute("articles", contentPageInfo);
        request.setAttribute("commons", commons);
        return "admin/article_list";
    }

    /**
     * 文章发表页面
     * @param request
     * @return
     */
    @GetMapping("/publish")
    public String newArticle(HttpServletRequest request) {
        List<Meta> categories = metaService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        return "admin/article_edit";
    }

    /**
     * 文章编辑页面
     * @param cid
     * @param request
     * @return
     */
    @GetMapping("/{cid}")
    public String editArticle(@PathVariable String cid, HttpServletRequest request) {
        Content content = contentService.getArticle(cid);
        request.setAttribute("contents", content);
        List<Meta> categories = metaService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("adminCommons", adminCommons);
        return "admin/article_edit";
    }

    @PostMapping("/publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse publishArticle(Content content, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("login_user");
        content.setAuthorId(user.getUid());
        content.setType(Types.ARTICLE.getType());
        if (StringUtils.isBlank(content.getCategories())) {
            content.setCategories("默认分类");
        }
        try {
            contentService.add(content);
        } catch (Exception e) {
            String msg = "文章发布失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }

    //功能与@PostMapping("/publish")类似
    @PostMapping("/modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse modifyArticle(Content content, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("login_user");
        content.setAuthorId(user.getUid());
        content.setType(Types.ARTICLE.getType());
        try {
            contentService.update(content);
        } catch (Exception e) {
            String msg = "文章编辑失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return  RestResponse.ok();
    }

    @RequestMapping("delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse delete(@RequestParam int cid, HttpServletRequest request, HttpSession session) {
        try {
            User user = (User) session.getAttribute("login_user");
            contentService.delete(cid);
            logService.add(LogActions.DEL_ARTICLE.getAction(), cid + "", request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            String msg = "文章删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }

}
