package com.ljf.blog.service;

import com.ljf.blog.pojo.User;

/**
 * Created by lujiafeng on 2018/9/11.
 */
public interface UserService {

    /**
     * 插入用户数据
     * @param user
     */
    void add(User user);

    /**
     * 根据uid删除用户
     * @param uid
     */
    void delete(int uid);

    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User get(int uid);

    /**
     * 更新user对象
     * @param user
     */
    void update(User user);

    /***
     * 登入
     * @param name
     * @param password
     */
    User login(String name, String password);
}
