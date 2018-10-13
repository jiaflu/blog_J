package com.ljf.blog.interceptor;

import com.ljf.blog.util.MyUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by lujiafeng on 2018/10/12.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //String[] excludes = new String[]{"/", "/admin/login", "/static/**"};
        registry.addInterceptor(baseInterceptor).excludePathPatterns(Arrays.asList("/static/**", "/templates/**"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ MyUtils.getUploadFilePath()+"upload/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates");

        super.addResourceHandlers(registry);
    }
}
