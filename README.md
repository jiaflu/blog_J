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





**数据库设计**

用户表 t_users

| 名称        | 类型    | 长度 | 主键  | 非空  | 描述       |
| ----------- | ------- | ---- | ----- | ----- | ---------- |
| uid         | int     | 10   | true  | true  | 主键，自增 |
| name        | varchar | 32   | false | false | 用户名     |
| password    | varchar | 64   | false | false | 密码       |
| email       | varchar | 200  | false | false | 邮箱地址   |
| home_url    | varchar | 200  | false | false |            |
| screen_name | varchar | 32   | false | false |            |
| created     | int     | 10   | false | false | 创建时间   |
| activated   | int     | 10   | false | false |            |
| logged      | int     | 10   | false | false |            |
| group_name  | varchar | 16   | false | false | 组名       |



文章表 t_contents

| 名称          | 类型    | 长度   | 主键  | 非空  | 描述       |
| ------------- | ------- | ------ | ----- | ----- | ---------- |
| cid           | int     | 10     | true  | true  | 主键,自增  |
| title         | varchar | 200    | false | false | 文章标题   |
| slug          | varchar | 200    | false | false | url地址    |
| creted        | int     | 10     | false | false | 创建时间   |
| modified      | int     | 10     | false | false | 修改时间   |
| content       | text    | 无限制 | false | false | 文章内容   |
| author_id     | int     | 10     | false | false | 作者ID     |
| type          | varchar | 16     | false | false | 文章类型   |
| status        | varchar | 16     | false | false | 文章状态   |
| categories    | varchar | 200    | false | false | 分类       |
| thumbImg      | varchar | 512    | false | false | 缩略图地址 |
| hits          | int     | 10     | false | false | 文章点击量 |
| comments_num  | int     | 10     | false | false | 评论数量   |
| allow_comment | int     | 1      | false | false | 允许评论   |

外键为 cid



标签表 t_metas

| 名称        | 类型    | 长度 | 主键  | 非空  | 描述       |
| ----------- | ------- | ---- | ----- | ----- | ---------- |
| mid         | int     | 10   | true  | true  | 主键，自增 |
| name        | varchar | 200  | false | false | 名称       |
| slug        | varchar | 200  | false | false | 说明       |
| type        | varchar | 200  | false | false | 类型       |
| description | varchar | 200  | false | false | 描述       |
| sort        | int     | 10   | false | false | 排序       |
| parent      | int     | 10   | false | false | 父标签     |

外键为 mid



评论表 t_comments





####service设计

**UserService**

- void add(User user);
- void delete(int uid);
- void get(int uid);
- void update(User user);
- User login(String name, String password);
  - 账户密码验证



**LogService**

- void add(Log log);
- void add(String action, String data, String ip, Integer author_id);
- List<Log> list(int page, int limit);
  - PageHelper.startPage(page, pageSize);









#### Controller

**AuthController**：用于admin（后台管理员）登入

- @GetMapping("/login")：返回登入界面
- @PostMapping("/login")：登入操作







