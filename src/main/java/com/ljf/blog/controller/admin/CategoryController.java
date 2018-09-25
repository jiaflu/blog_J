package com.ljf.blog.controller.admin;

import com.ljf.blog.bo.RestResponse;
import com.ljf.blog.dto.Types;
import com.ljf.blog.exception.ExceptionHelper;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.pojo.Meta;
import com.ljf.blog.service.MetaService;
import com.ljf.blog.util.AdminCommons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lujiafeng on 2018/9/18.
 */
@Controller
@RequestMapping("admin/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    MetaService metaService;
    @Autowired
    AdminCommons adminCommons;

    @GetMapping("")
    public String index(HttpServletRequest request) {
        //分类
        List<Meta> categories = metaService.getMetas(Types.CATEGORY.getType());
        //标签
        List<Meta> tags = metaService.getMetas(Types.TAG.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        request.setAttribute("adminCommons", adminCommons);
        return "admin/category";
    }

    @PostMapping("/save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse save(@RequestParam String cname, @RequestParam Integer mid) {
        try {
            metaService.saveMeta(Types.CATEGORY.getType(), cname, mid);
        } catch (Exception e) {
            String msg = "分类保存失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }

    @PostMapping("/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponse delete(@RequestParam Integer mid) {
        try {
            metaService.delete(mid);
        } catch (Exception e) {
            String msg = "删除分类失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponse.ok();
    }


}
