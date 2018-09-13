####概述
基于spring boot 开发的个人博客网站练手项目



#### 开发流程

##### 数据库CURD

- 编写sql语句（创建数据库和表）
- 使用MyBatis逆向工程实现CURD操作
- springboot整合MyBatis
- 编写service层，根据需求分析和概要设计，将具体业务转换成代码
- 关于事务的使用，采用spring的`@Transactional`



**SpringBoot 整合 Mybatis 注意事项**

- `pom.xml`添加mybatis依赖：

```xml
		<!-- mybatis -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.2.0</version>
		</dependency>

		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.5</version>
		</dependency>

		<!-- mybatis 逆向工程 -->
		<dependency>
			<groupId>org.mybatis.generator</groupId>
			<artifactId>mybatis-generator-core</artifactId>
			<version>1.3.5</version>
		</dependency>
```

- `application.properties`

  - jdbc配置

  - 实体类包路径

  - ```properties
    mybatis.type-aliases-package=com.ljf.blog.pojo
    ```

  - mapper xml 映射文件

  - ```properties
    mybatis.mapper-locations=classpath:mapper/*.xml
    ```

- Application应用启动类添加`@MapperScan`注解

- ```java
  @MapperScan("com.ljf.blog.mapper")
  ```



**静态资源访问**

springboot 默认提供



**控制层：Controller**

1）登入控制

- login：返回login登入界面
- doLogin：登入操作
- logout：登出操作