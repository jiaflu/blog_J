package com.ljf.blog.interceptor;

import com.ljf.blog.constant.WebConst;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.UserService;
import com.ljf.blog.util.AdminCommons;
import com.ljf.blog.util.Commons;
import com.ljf.blog.util.IpUtil;
import com.ljf.blog.util.MyUtils;
import com.sun.tools.internal.xjc.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lujiafeng on 2018/10/12.
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Autowired
    private UserService userService;

    @Autowired
    private Commons commons;

    @Autowired
    private AdminCommons adminCommons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String ip = IpUtil.getIpAddrByRequest(request);
        logger.info("UserAgent: {}", request.getHeader(USER_AGENT));
        logger.info("用户访问地址: {}, 来路地址: {}", uri, ip);

        //请求拦截处理
        User user = MyUtils.getLoginUser(request);

        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && null == user) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String ip = IpUtil.getIpAddrByRequest(request);
        //禁止该ip访问
        if (WebConst.BLOCK_IPS.contains(ip)) {
            // 强制跳转
            modelAndView.setViewName("comm/ipban");
        }
        request.setAttribute("commons", commons);
        request.setAttribute("adminCommons", adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
