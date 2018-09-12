package com.ljf.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljf.blog.mapper")
public class BlogJApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogJApplication.class, args);
	}
}
