<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project12-javaweb-servlet-api README

<!-- code_chunk_output -->

- [Java Web project12-javaweb-servlet-api README](#java-web-project12-javaweb-servlet-api-readme)
- [第 12 章 project12-javaweb-servlet-api 模块知识点](#第-12-章-project12-javaweb-servlet-api-模块知识点)
  - [12.1 Servlet 生命周期：实例化、初始化、服务、销毁](#121-servlet-生命周期实例化-初始化-服务-销毁)
    - [12.1.1 Servlet 生命周期中的初始化方法](#1211-servlet-生命周期中的初始化方法)
  - [12.2 Servlet 中的 ServletContext 和 < context-param >](#122-servlet-中的-servletcontext-和--context-param-)
    - [12.2.1 获取 ServletContext 的方法](#1221-获取-servletcontext-的方法)
    - [12.2.2 获取初始化值](#1222-获取初始化值)

<!-- /code_chunk_output -->

# 第 12 章 project12-javaweb-servlet-api 模块知识点

## 12.1 Servlet 生命周期：实例化、初始化、服务、销毁

### 12.1.1 Servlet 生命周期中的初始化方法

1. Servlet 生命周期中的初始化方法有两个：init()、init(config)。

   - 有参数的 init 方法代码如下：

     ```java
     public void init(ServletConfig config) throws ServletException
     {
         this.config = config ;
         init();
     }
     ```

   - 无参数的 init 方法如下：

     ```java
     public void init() throws ServletException
     {

     }
     ```

   - 如果想要在 Servlet 初始化时做一些准备工作，执行一些自定义的操作，那么可以重写 init 方法，可以通过如下步骤去获取初始化设置的数据。

     - 获取 config 对象：ServletConfig servletConfig = getServletConfig();

     - 获取初始化参数值： String initValue = servletConfig.getInitParameter(key);

2. 在 web.xml 文件中配置 Servlet。

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
       <servlet>
           <servlet-name>Demo1Servlet</servlet-name>
           <servlet-class>com.myxh.servlets.Demo1Servlet</servlet-class>
           <init-param>
               <param-name>Hello</param-name>
               <param-value>World</param-value>
           </init-param>

           <init-param>
               <param-name>name</param-name>
               <param-value>MYXH</param-value>
           </init-param>
       </servlet>

       <servlet-mapping>
           <servlet-name>Demo1Servlet</servlet-name>
           <url-pattern>/demo1_servlet</url-pattern>
       </servlet-mapping>

       <context-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:applicationContext.xml</param-value>
       </context-param>
   </web-app>
   ```

3. 也可以通过注解 @WebServlet 的方式进行配置。

   ```java
   @WebServlet(urlPatterns = {"/demo1_servlet"},
           initParams = {
                   @WebInitParam(name = "Hello", value = "World"),
                   @WebInitParam(name = "name", value = "MYXH"),
           }
   )
   ```

## 12.2 Servlet 中的 ServletContext 和 < context-param >

通过 ServletContext 获取配置的上下文参数。

### 12.2.1 获取 ServletContext 的方法

- 在初始化 init 方法中： ServletContxt servletContext = getServletContext();

- 在服务 service 方法中可以通过 request 对象获取，也可以通过 session 获取：

  - ServletContext servletContext = request.getServletContext();

  - ServletContext servletContext = request.getSession().getServletContext();

### 12.2.2 获取初始化值

- String contextConfigLocation = servletContext.getInitParameter(key);
