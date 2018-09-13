package com.ljf.blog.controller.admin;

import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.dto.LogActions;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.LogService;
import com.ljf.blog.service.UserService;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lujiafeng on 2018/9/12.
 *
 * admin登入
 */

@Controller
@RequestMapping("/admin")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    LogService logService;

    @GetMapping("/test")
    public String test() {
        return "admin/test";
    }


    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RestResponse doLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        username = HtmlUtils.htmlEscape(username);
        password = HtmlUtils.htmlEscape(password);

        try {
            //登入失败则抛出错误
            User user = userService.login(username, password);
            request.getSession().setAttribute("login_user", user);
            logService.add(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            String msg = null;
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                msg = "登入失败";
            }
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletRequest request,HttpServletResponse response) {
        session.removeAttribute("login_user");
    }

}
