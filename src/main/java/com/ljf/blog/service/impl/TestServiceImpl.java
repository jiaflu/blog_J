package com.ljf.blog.service.impl;

import com.ljf.blog.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by lujiafeng on 2018/9/11.
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void say() {
        System.out.println("hello");
    }
}
