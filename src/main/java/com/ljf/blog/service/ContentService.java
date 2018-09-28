package com.ljf.blog.service;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.ContentExample;

/**
 * Created by lujiafeng on 2018/9/11.
 */
public interface ContentService {
    /**
     * 发布文章
     * @param content
     */
    void add(Content content);


    /**
     * 根据id读取文章（单篇）
     * @param id
     * @return
     */
    Content getArticle(String id);


    /**
     * 读取文章（多篇）
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Content> getArticles(Integer page, Integer limit);

    /**
     * 读取文章（根据分类）
     * @param mid
     * @param page
     * @param limit
     * @return
     */
    //PageInfo<Content> getArticles(Integer mid, Integer page, Integer limit);

    /***
     * 读取文章（根据文章标题关键字）
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Content> getArticles(String keyword, Integer page, Integer limit);

    /***
     * 读取文章（根据example）
     * @param example
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Content> getArticles(ContentExample example, Integer page, Integer limit);


    /**
     * 更新文章
     * @param content
     */
    void update(Content content);


    /**
     * 根据id删除文章
     * @param cid
     */
    void delete(Integer cid);

//    /**
//     * 更新原因文章的category
//     * @param ordinal
//     * @param newCatefory
//     */
//    void updateCategory(String ordinal, String newCatefory);
}
