package com.ljf.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.constant.WebConst;
import com.ljf.blog.controller.admin.IndexController;
import com.ljf.blog.dto.Types;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.Comment;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Meta;
import com.ljf.blog.service.CommentService;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.MetaService;
import com.ljf.blog.service.SiteService;
import com.ljf.blog.util.Commons;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
        PageInfo<Content> articles = contentService.getContents(p, limit);
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
        Content content = contentService.getContent(cid);
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
    }

    /**
     * 文章内容预览页
     * @param request
     * @param cid
     * @return
     */
    @GetMapping(value = {"article/{cid}/preview", "article/{cid}.html"})
    public String articlePreview(HttpServletRequest request, @PathVariable String cid) {
        Content content = contentService.getContent(cid);
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



    /**
     * 更新点击次数
     *
     * @param cid
     * @param chits
     */
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
