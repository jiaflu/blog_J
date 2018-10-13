package com.ljf.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.constant.WebConst;
import com.ljf.blog.controller.admin.IndexController;
import com.ljf.blog.dto.Types;
import com.ljf.blog.exception.ExceptionHelper;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Meta;
import com.ljf.blog.service.CommentService;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.MetaService;
import com.ljf.blog.service.SiteService;
import com.ljf.blog.util.Commons;
import com.ljf.blog.util.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by lujiafeng on 2018/9/25.
 */
@Controller
@RequestMapping("")
public class ForeController {

    private static final Logger logger = LoggerFactory.getLogger(ForeController.class);
    public static String THEME = "themes/jantent";

    @Autowired
    ContentService contentService;
    @Autowired
    CommentService commentService;
    @Autowired
    SiteService siteService;
    @Autowired
    MetaService metaService;
    @Autowired
    Commons commons;

    /***
     * 博客首页
     * @param request
     * @param limit
     * @return
     */
    @GetMapping("/")
    private String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return this.index(request, 1, limit);
    }

    @GetMapping(value = "page/{p}")
    public String index(HttpServletRequest request, @PathVariable int p, @RequestParam(value = "limit", defaultValue = "10") int limit) {
        // 开启thymeleaf缓存，加快访问速度
        p = p < 0 || p > WebConst.MAX_PAGE ? 1 : p;
        PageInfo<Content> articles = contentService.getArticles(p, limit);
        //List<Meta> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
        List<Meta> categories = metaService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("articles", articles);
        request.setAttribute("commons", commons);
        if (p > 1) {
            request.setAttribute("title", "第" + p + "页");
        }
        return THEME + "/index";
    }

    /**
     * 文章内容页
     * @param request
     * @param cid
     * @return
     */
    @GetMapping(value = {"article/{cid}", "article/{cid}.html"})
    public String getArticle(HttpServletRequest request, @PathVariable String cid) {
        Content content = contentService.getArticle(cid);
        if (null == content || "draft".equals(content.getStatus())) {
            //404
            return "404 not found";
        }
        request.setAttribute("article", content);
        request.setAttribute("is_post", true);
        request.setAttribute("commons", commons);
        completeArticle(request, content);
        //updateArticleHit(contents.getCid(), contents.getHits());
        return THEME + "/page";
        //return THEME + "/post";
    }

    /**
     * 文章内容预览页
     * @param request
     * @param cid
     * @return
     */
    @GetMapping(value = {"article/{cid}/preview", "article/{cid}.html"})
    public String articlePreview(HttpServletRequest request, @PathVariable String cid) {
        Content content = contentService.getArticle(cid);
        if (null == content || "draft".equals(content.getStatus())) {
            //404
            return "404 not found";
        }
        request.setAttribute("article", content);
        request.setAttribute("is_post", true);
        request.setAttribute("commons", commons);
        completeArticle(request, content);
        //updateArticleHit(contents.getCid(), contents.getHits());
        return THEME + "/post";
    }

    @RequestMapping("logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        try {
            response.sendRedirect(Commons.site_title());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


//    /**
//     * 更新点击次数
//     *
//     * @param cid
//     * @param chits
//     */
//    @Transactional(rollbackFor = TipException.class)
//    protected void updateArticleHit(Integer cid, Integer chits) {
//        Integer hits = cache.hget("article", "hits");
//        if (chits == null) {
//            chits = 0;
//        }
//        hits = null == hits ? 1 : hits + 1;
//        if (hits >= WebConst.HIT_EXCEED) {
//            ContentVo temp = new ContentVo();
//            temp.setCid(cid);
//            temp.setHits(chits + hits);
//            contentService.updateContentByCid(temp);
//            cache.hset("article", "hits", 1);
//        } else {
//            cache.hset("article", "hits", 1);
//        }
//    }


    @PostMapping("comment")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse comment(HttpServletRequest request, HttpServletResponse response,
                                Integer cid, Integer coid, String author, String mail, String url, String text, String _csrf_token) {
        //获取来源页地址
//        String ref = request.getHeader("Referer");
//        if (StringUtils.isBlank(ref) || StringUtils.isBlank(_csrf_token)) {
//            return RestResponse.fail("Bad request");
//        }
//        String token = cache.hget(Types.CSRF_TOKEN.getType(), _csrf_token);
//        if (StringUtils.isBlank(token)) {
//            return RestResponseBo.fail("Bad request");
//        }
        if (null == cid || StringUtils.isBlank(text)) {
            return RestResponse.fail("请输入完整后评论");
        }
        if (StringUtils.isNotBlank(author) && author.length() > 50) {
            return RestResponse.fail("姓名过长");
        }
        if (StringUtils.isNotBlank(mail) && !MyUtils.isEmail(mail)) {
            return RestResponse.fail("请输入正确的邮箱格式");
        }
        if (text.length() > 200) {
            return RestResponse.fail("请输入200个字符以内的评论");
        }
        //检验性判断
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setCid(cid);
        comment.setIp(request.getRemoteAddr());
        comment.setUrl(url);
        comment.setContent(text);
        comment.setMail(mail);
        comment.setParent(coid);
        try {
            commentService.add(comment);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = "评论发布失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }


    }


    /**
     * 查询文章的评论信息，并补充到里面，返回前端
     *
     * @param request
     * @param content
     */
    private void completeArticle(HttpServletRequest request, Content content) {
        if (content.getAllowComment()) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            request.setAttribute("cp", cp);
            PageInfo<Comment> commentsPaginator = commentService.getComments(content.getCid(), Integer.parseInt(cp), 6);
            request.setAttribute("comments", commentsPaginator);
        }
    }
}
