package com.ljf.blog.service.impl;

import com.ljf.blog.exception.TipException;
import com.ljf.blog.mapper.UserMapper;
import com.ljf.blog.pojo.User;
import com.ljf.blog.pojo.UserExample;
import com.ljf.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {

        userMapper.insert(user);
    }

    @Override
    public void delete(int uid) {
        userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public User get(int uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User login(String name, String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new TipException("用户名或密码为空");
        }

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);
        example.setOrderByClause("uid desc");
        List<User> ls = userMapper.selectByExample(example);
        if (ls.isEmpty()) {
            throw new TipException("用户名或密码错误");
        }
        return ls.get(0);
    }
}
