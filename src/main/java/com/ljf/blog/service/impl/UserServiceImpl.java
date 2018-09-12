package com.ljf.blog.service.impl;

import com.ljf.blog.mapper.UserMapper;
import com.ljf.blog.pojo.User;
import com.ljf.blog.pojo.UserExample;
import com.ljf.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);
        example.setOrderByClause("uid desc");
        List<User> ls = userMapper.selectByExample(example);
        if (ls.isEmpty()) {
            return null;
        }
        return ls.get(0);
    }
}
