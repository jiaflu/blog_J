package com.ljf.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljf.blog.dto.LogActions;
import com.ljf.blog.mapper.ContentMapper;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Log;
import com.ljf.blog.pojo.User;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.LogService;
import com.ljf.blog.service.TestService;
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
	TestService testService;
	@Autowired
	LogService logService;
	@Autowired
	ContentService contentService;


	@Test
	public void test() {
		Content content = new Content();
		content.setTitle("blog_test3");
		content.setHits(121);
		content.setCategories("springboot");
		content.setStatus("已发布");
		content.setSlug("qwe");
		//contentMapper.insert(content);
		testService.say();
	}


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
	public void listLogs() {
		List<Log> logs1 = logService.list(1,3);
		for (Log l : logs1) {
			System.out.println(l.getCreated());
		}
		System.out.println("-------------------------");
		List<Log> logs2 = logService.list(2,3);
		for (Log l : logs2) {
			System.out.println(l.getCreated());
		}
	}

	@Test
	public void listContents() {
		PageInfo<Content> pageInfo = contentService.getContents(1,5);
		System.out.println(pageInfo.getSize());
		List<Content> contentList = pageInfo.getList();
		for (Content content: contentList) {
			System.out.println(content.getTitle());
		}
	}
}
