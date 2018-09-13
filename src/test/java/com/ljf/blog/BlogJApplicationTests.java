package com.ljf.blog;

import com.github.pagehelper.PageHelper;
import com.ljf.blog.dto.LogActions;
import com.ljf.blog.mapper.ContentMapper;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.Log;
import com.ljf.blog.pojo.User;
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
	ContentMapper contentMapper;


	@Test
	public void test() {
		Content content = new Content();
		content.setTitle("blog_test");
		content.setHits(100);
		content.setCategories("springboot");
		content.setStatus("已发布");
		content.setSlug("slugg");
		contentMapper.insert(content);
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
//		for (int i=0; i < 50; i++) {
//			logService.add(LogActions.LOGIN.getAction(), "i: " + i, "0:0:0:0:0:0:0:1", 2);
//		}
		List<Log> logs = logService.list(0,5);
		for (Log l : logs) {
			System.out.println(l.getCreated());
		}
		System.out.println("-------------------------");
		logs = logService.list(1,5);
		for (Log l : logs) {
			System.out.println(l.getCreated());
		}
	}
}
