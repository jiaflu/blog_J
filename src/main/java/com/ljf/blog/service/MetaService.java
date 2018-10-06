package com.ljf.blog.service;

import com.ljf.blog.pojo.Meta;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/11.
 */
public interface MetaService {
    /**
     * 根据类型和名字查询项
     * @param type
     * @param name
     * @return
     */
    Meta getMeta(String type, String name);

    /**
     * 根据类型查询项目列表
     * @param type
     * @return
     */
    List<Meta> getMetas(String type);


    /**
     * 保存多个项目
     * @param cid
     * @param names
     * @param type
     */
    void saveMeta(Integer cid, String names, String type);

    /**
     * 保存
     * @param type
     * @param name
     * @param mid
     */
    void saveMeta(String type, String name, Integer mid);

    /**
     * 删除项目
     * @param mid
     */
    void delete(Integer mid);

    /**
     * 更新项目
     * @param meta
     */
    void update(Meta meta);

    /**
     * 更新count
     * @param category
     */
    void updateCount(String category, int increment);
}
