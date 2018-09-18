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
}
