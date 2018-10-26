package com.ljf.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.ljf.blog.mapper")  //启动类添加注解扫描
public class BlogJApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogJApplication.class, args);
	}
}
