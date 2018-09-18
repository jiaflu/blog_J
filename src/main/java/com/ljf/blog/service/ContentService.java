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
    void publish(Content content);

    /**
     * 查询文章返回对跳数据
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Content> getContents(int page, int limit);

    /**
     * 根据id读取文章
     * @param id
     * @return
     */
    Content getContent(String id);
//
//    /**
//     * 更新文章
//     * @param content
//     */
//    void update(Content content);
//
//    /**
//     * 查询分类/标签下的文章归档
//     * @param mid
//     * @param page
//     * @param limit
//     * @return
//     */
//    PageInfo<Content> getArticles(int mid, int page, int limit);
//
//    /**
//     * 搜索、分页
//     * @param keyword
//     * @param page
//     * @param limit
//     * @return
//     */
//    PageInfo<Content> getArticles(String keyword, int page, int limit);
//
//    /**
//     *
//     * @param contentExample
//     * @param page
//     * @param limit
//     * @return
//     */
//    PageInfo<Content> getArticlesWithpage(ContentExample contentExample, int page, int limit);
//
    /**
     * 根据id删除文章
     * @param cid
     */
    void delete(int cid);
//
//    void updateArticles(Content content);
//
//    /**
//     * 更新原因文章的category
//     * @param ordinal
//     * @param newCatefory
//     */
//    void updateCategory(String ordinal, String newCatefory);
}
