package com.ljf.blog;

import com.ljf.blog.pojo.User;
import com.ljf.blog.service.TestService;
import com.ljf.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogJApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	TestService testService;

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
	public void test() {
		testService.say();
	}
}
