<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project17-javaweb-qqzone README

<!-- code_chunk_output -->

- [Java Web project17-javaweb-qqzone README](#java-web-project17-javaweb-qqzone-readme)
- [第 17 章 project17-javaweb-qqzone 模块知识点](#第-17-章-project17-javaweb-qqzone-模块知识点)
  - [17.1 Java 日期和字符串之间的格式化](#171-java-日期和字符串之间的格式化)
    - [17.1.1 Java 字符串转换为日期](#1711-java-字符串转换为日期)
    - [17.1.2 Java 日期转换为字符串](#1712-java-日期转换为字符串)
  - [17.2 Thymeleaf 日期和字符串之间的格式化](#172-thymeleaf-日期和字符串之间的格式化)
  - [17.3 程序启动时访问的页面](#173-程序启动时访问的页面)
  - [17.4 程序启动时访问的页面的过程](#174-程序启动时访问的页面的过程)
  - [17.5 目前进行 Javaweb 项目开发的流程](#175-目前进行-javaweb-项目开发的流程)
    - [17.5.1 使用通用代码，复制 ssm 包](#1751-使用通用代码复制-ssm-包)
    - [17.5.2 新建配置文件 applicationContext.xml](#1752-新建配置文件-applicationcontextxml)
    - [17.5.3 在 web.xml 文件中配置](#1753-在-webxml-文件中配置)
    - [17.5.4 开发具体的业务模块](#1754-开发具体的业务模块)
  - [17.6 修改 JdbcUtils 的 druid.properties 文件](#176-修改-jdbcutils-的-druidproperties-文件)

<!-- /code_chunk_output -->

# 第 17 章 project17-javaweb-qqzone 模块知识点

## 17.1 Java 日期和字符串之间的格式化

### 17.1.1 Java 字符串转换为日期

```java
// String -> Date
String dateStr = "2023-07-21 12:00:00";

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

try
{
    Date date = sdf.parse(dateStr);
}
catch (ParseException e)
{
    e.printStackTrace();
}
```

```java
// String -> LocalDateTime
String dateStr = "2023-07-21 12:00:00";

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
```

### 17.1.2 Java 日期转换为字符串

```java
// Date -> String
Date date = new Date();

String dateStr = sdf.format(date);
```

```java
// LocalDateTime -> String
LocalDateTime now = LocalDateTime.now();

String dateStr = now.format(formatter);
```

## 17.2 Thymeleaf 日期和字符串之间的格式化

1. 首先在 POJO 中编写 getTopicDateAsDate 方法，用 Timestamp.valueOf() 方法把 Data 类转化为 LocalDateTime 类。

   ```java
    public Date getTopicDateAsDate()
    {
        return Timestamp.valueOf(topicDate);
    }
   ```

2. 然后在 Thymeleaf 中使用 #dates 这个公共的内置对象，来格式化 Date 类。

   ```html
   <td th:text="${#dates.format(topic.topicDateAsDate, 'yyyy-MM-dd HH:mm:ss')}">
     2023-07-17 14:19:00
   </td>
   ```

## 17.3 程序启动时访问的页面

- 系统启动时，浏览器访问的页面是：
  http://localhost:8080/project17-javaweb-qqzone/page.do?operate=page&page=login

- 为什么不是：
  http://localhost:8080/project17-javaweb-qqzone/login.html

- 如果是后者，属于直接访问静态页面，那么浏览器不能识别页面上的 thymeleaf 表达式（标签），访问前者的目的其实就是要执行 ViewBaseServlet 类中的 processTemplete() 方法。

## 17.4 程序启动时访问的页面的过程

> http:// localhost :8080 /project17-javaweb-qqzone /page.do ?operate=page&page=login

- http://：网络协议。

- localhost：ServerIP（服务器 IP）。

- :8080：port（端口）。

- /project17-javaweb-qqzone：context root（上下文目录）。

- /page.do：request.getServletPath()。

- ?operate=page&page=login：query string（查询字符串）。

访问这个（http://localhost:8080/project17-javaweb-qqzone/page.do?operate=page&page=login） URL，执行的过程：

1. DispatcherServlet -> urlPattern：\*.do，拦截 /page.do。

2. request.getServletPath() -> /page.do。

3. 解析处理字符串，将 /page.do -> page。

4. 拿到 page 这个字符串，然后去 IOC 容器（BeanFactory）中寻找 id=page 的那个 bean 对象 -> PageController.java。

5. 获取 operate 的值 -> page 因此得知，应该执行 PageController 中的 page() 方法。

6. PageController 中的 page 方法定义如下：

   ```java
   public String page(String page)
   {
       return page;
   }
   ```

7. 在 queryString：?operate=page&page=login 中获取请求参数，参数名是 page，参数值是 login，因此 page 方法的参数 page 值会被赋上 login，然后 return 字符串 "login"。

8. 因为 PageController 的 page 方法是 DispatcherServlet 通过反射调用的 method.invoke()，因此字符串 "login" 返回给 DispatcherServlet。

9. DispatcherServlet 接收到返回值，然后处理视图，目前处理视图的方式有两种：

   - 带前缀 redirect:

   - 不带前缀

   - 当前返回 "login"，不带前缀，那么执行 super.processTemplete("login",request,response) 方法。

10. 此时 ViewBaseServlet 中的 processTemplete 方法会执行，在 "login" 这个字符串前面拼接 "/"（其实就是配置文件中 view-prefixe 配置的值），在 "login" 这个字符串后面拼接 ".html"（其实就是配置文件中 view-suffix 配置的值），最后进行服务器转发。

## 17.5 目前进行 Javaweb 项目开发的流程

### 17.5.1 使用通用代码，复制 ssm 包

### 17.5.2 新建配置文件 applicationContext.xml

新建配置文件 applicationContext.xml，或者可以重命名，在 web.xml 中指定文件名。

### 17.5.3 在 web.xml 文件中配置

1. 配置前缀和后缀，这样 thymeleaf 引擎就可以根据我们返回的字符串进行拼接，再进行跳转。

   ```xml
    <context-param>
        <param-name>view-prefix</param-name>
        <param-value>/</param-value>
    </context-param>

    <context-param>
        <param-name>view-suffix</param-name>
        <param-value>.html</param-value>
    </context-param>
   ```

2. 配置监听器要读取的参数，目的是加载 IOC 容器的配置文件（也就是 applicationContext.xml）。

   ```xml
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>applicationContext.xml</param-value>
    </context-param>
   ```

### 17.5.4 开发具体的业务模块

1. 一个具体的业务模块纵向上由几个部分组成：

   - HTML 页面。

   - POJO 类。

   - DAO 接口和实现类。

   - Service 接口和实现类。

   - Controller 控制器组件。

2. 如果 html 页面中有 thymeleaf 表达式，一定不能够直接访问，必须要经过 PageController。

3. 在 applicationContext.xml 中配置 DAO、Service、Controller，以及三者之间的依赖关系。

4. DAO 实现类中，继承 BaseDAO 类，然后实现具体的接口, 需要注意 BaseDAO 后面的泛型不能写错，例如：

   ```java
   public class UserBasicDaoImpl extends BaseDao<UserBasic> implements UserBasicDao
   {
        // ...
   }
   ```

5. Service 是业务控制类，这一层需要注意：

   - 业务逻辑我们都封装在 Service 这一层，不要分散在 Controller 层，也不要出现在 DAO 层（需要保证 DAO 方法的单精度特性）。

   - 当某一个业务功能需要使用其他模块的业务功能时，尽量的调用其他模块的 Service，而不是深入到其他模块的 DAO 细节。

6. Controller 类的编写规则：

   - 在 applicationContext.xml 中配置 Controller。

     ```xml
     <bean id="user" class="com.myxh.qqzone.controller.UserController">
         <property name="userBasicService" ref="userBasicService"/>
         <property name="topicService" ref="topicService"/>
     </bean>
     ```

     那么用户在前端发请求时，对应的 servletpath 就是 /user.do，其中的 “user” 就是对应此处的 bean 的 id 值。

   - 在 Controller 中编写的方法名需要和 operate 的值一致。

     ```java
     public String login(String loginId , String password , HttpSession session)
     {
         // ...

         return "index";
     }
     ```

     因此，登录验证的表单如下：

     ```html
     <form th:action="@{/user.do}" method="post">
       <input type="hidden" name="operate" value="login" />
     </form>
     ```

   - 在表单中组件的 name 属性和 Controller 中方法的参数名一致。

     ```html
     <input type="text" name="loginId" />
     ```

     ```java
     public String login(String loginId , String password , HttpSession session)
     {
         // ...

         return "index";
     }
     ```

   - 另外需要注意的是： Controller 中的方法中的参数不一定都是通过请求参数获取的。

     ```java
     if("request".equals())
     {
         // 直接赋值
     }
     else if("response".equals()
     {
         // 直接赋值
     }
     else if("session".equals()
     {
         // 直接赋值
     }
     else
     {
         // 此处才是从 request 的请求参数中获取
         request.getParameter("loginId")

         // ...
     }
     ```

7. DispatcherServlet 中步骤大致分为：

   - ① 从 application 作用域获取 IOC 容器。

   - ② 解析 servletPath，在 IOC 容器中寻找对应的 Controller 组件。

   - ③ 准备 operate 指定的方法所要求的参数。

   - ④ 调用 operate 指定的方法。

   - ⑤ 接收到执行 operate 指定的方法的返回值，对返回值进行处理，视图处理。

8. 为什么 DispatcherServlet 能够从 application 作用域获取到 IOC 容器？

   - ContextLoaderListener 在容器启动时会执行初始化任务，而它的操作是：

     - 解析 IOC 的配置文件，创建一个一个的组件，并完成组件之间依赖关系的注入。

     - 将 IOC 容器保存到 application 作用域。

## 17.6 修改 JdbcUtils 的 druid.properties 文件

修改 JdbcUtils 的 druid.properties 文件，让其使用 Druid 数据源连接池来连接 MySQL 数据库。

1. 直接配置 properties，读取后加载驱动。

2. 使用 Druid 连接池技术，那么 properties 中的 key 是对应的。

   ```properties
   # key = value -> Java Properties \u8BFB\u53D6 (key \u6216 value)
   # druid \u914D\u7F6E\u7684 key \u56FA\u5B9A\u547D\u540D
   # druid \u8FDE\u63A5\u6C60\u9700\u8981\u7684\u914D\u7F6E\u53C2\u6570, key \u56FA\u5B9A\u547D\u540D
   driverClassName=com.mysql.cj.jdbc.Driver
   url=jdbc:mysql:///my_qqzone
   username=MYXH
   password=520.ILY!
   initialSize=5
   maxActive=10
   ```
