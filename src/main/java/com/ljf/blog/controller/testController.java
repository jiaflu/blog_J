package com.ljf.blog.controller;

import com.ljf.blog.pojo.User;
import com.ljf.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lujiafeng on 2018/9/11.
 */

@Controller
@RequestMapping("")
public class testController {
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        User user = userService.get(2);
        return user.getEmail();
    }
}
