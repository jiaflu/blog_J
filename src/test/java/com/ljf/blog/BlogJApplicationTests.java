package com.ljf.blog;

import com.github.pagehelper.PageInfo;
import com.ljf.blog.constant.WebConst;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.LogService;
import com.ljf.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogJApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	LogService logService;
	@Autowired
	ContentService contentService;

	@Test
	public void contentTest() {
		PageInfo<Content> contentPageInfo = contentService.getArticles(1, 5);
		List<Content> contents = contentPageInfo.getList();
		System.out.println("1:" + contents.get(0).getTitle());
		System.out.print("2:" + contents.get(0).getContent());
//		Content content = contentService.getArticle("" + 7);
//		System.out.println(content.getContent());
	}

	@Test
	public void commonsTest() {
		String str = WebConst.initConfig.get("site_url");
		System.out.println(str);
	}



}
