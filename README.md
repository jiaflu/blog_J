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

| 名称          | 类型     | 长度       | 主键      | 非空      | 描述         |
| ------------- | -------- | ---------- | --------- | --------- | ------------ |
| cid           | int      | 10         | true      | true      | 主键,自增    |
| title         | varchar  | 200        | false     | false     | 文章标题     |
| slug          | varchar  | 200        | false     | false     | url地址      |
| creted        | int      | 10         | false     | false     | 创建时间     |
| modified      | int      | 10         | false     | false     | 修改时间     |
| **content**   | **text** | **无限制** | **false** | **false** | **文章内容** |
| author_id     | int      | 10         | false     | false     | 作者ID       |
| type          | varchar  | 16         | false     | false     | 文章类型     |
| status        | varchar  | 16         | false     | false     | 文章状态     |
| categories    | varchar  | 200        | false     | false     | 分类         |
| thumbImg      | varchar  | 512        | false     | false     | 缩略图地址   |
| hits          | int      | 10         | false     | false     | 文章点击量   |
| comments_num  | int      | 10         | false     | false     | 评论数量     |
| allow_comment | int      | 1          | false     | false     | 允许评论     |

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

**UserService**：

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

**ContentService**

- void add(Content content); //添加（发布）文章
- Content getArticle(String id); //根据主键获取文章
- PageInfo<Content> getArticles(Integer page, Integer limit); //获取文章，不区分状态
- PageInfo<Content> getArticles(String keyword, Integer page, Integer limit);//根据文章title关键字搜索获取文章
- PageInfo<Content> getArticles(ContentExample example, Integer page, Integer limit);
- void update(Content content);
- void delete(Integer cid);

**MetaService**

- Meta getMeta(String type, String name); //根据type和name获取分类信息（唯一）
- List<Meta> getMetas(String type); //根据type获取分类信息
- void saveMeta(String type, String name, Integer mid);  //插入分类信息
  - mid可选，如为null则自动生成
- void delete(Integer mid);
- void update(Meta meta);

**CommentService**

- void add(Comment comment);
- Comment getComment(Integer coid);
- PageInfo<Comment> getComments(Integer cid, Integer page, Integer limit);  //获取文章cid对应下面的评论
- PageInfo<Comment> getComments(CommentExample commentExample, Integer page, Integer limit);
- void delete(Integer coid, Integer cid);
- void update(Comment comment);







#### Controller

**AuthController  @RequestMapping("/admin")**：用于admin（后台管理员）登入

- @GetMapping("/login")：返回登录界面
- @PostMapping("/login")：登录操作
  - 缓存cache存放密码错误次数error_count
  - cookie
  - 登录成功则把账户信息存入session
- @GetMapping("/logout")：登出操作

**ArticleController  @RequestMapping("/admin/article")**：关于文章的操作

- @GetMapping("")：返回文章列表页面
  - return "admin/article_list";
- @GetMapping("/publish")：新发布文章，返回文章编辑页面
  - return "admin/article_edit";
- @GetMapping("/{cid}")：编辑文章，数据库查询已有文章，展示到文章编辑页面
  - return "admin/article_edit";
- @PostMapping("/publish")：发布文章
  - 修改文章状态
- @PostMapping("/modify")：修改文章
- @RequestMapping("delete")：删除文章

**CategoryController  @RequestMapping("admin/category")**：关于分类的操作

- @GetMapping("")：返回分类首页
- @PostMapping("/save")：保存分类
- @PostMapping("/delete")：删除分类







**util包**

- AdminCommons
  - rand_color()：随机产生颜色选择



- Commons
  - **WebConst中initConfig初始化时为空,但运行后`"site_url"`键对应的值为`localhost:8080**`

```java
public static String site_option(String key, String defaultValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String str = WebConst.initConfig.get(key);
        if (StringUtils.isNotBlank(str)) {
            //System.out.println(str);
            return str;
        } else {
            return defaultValue;
        }
    }
```



