package com.ljf.blog;

import com.github.pagehelper.PageInfo;
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
	public void loginTest() {
		String name = "ljf";
		String password = "123456";
		User user = userService.login(name, password);
		if (null != user) {
			System.out.println(user.getEmail());
		} else {
			System.out.println("null");
		}
	}


	@Test
	public void listContents() {
		PageInfo<Content> pageInfo = contentService.getArticles(1,5);
		System.out.println(pageInfo.getSize());
		List<Content> contentList = pageInfo.getList();
		for (Content content: contentList) {
			System.out.println(content.getTitle());
		}
	}


}
