<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project13-javaweb-filter-listener README

<!-- code_chunk_output -->

- [Java Web project13-javaweb-filter-listener README](#java-web-project13-javaweb-filter-listener-readme)
- [第 13 章 project13-javaweb-filter-listener 模块知识点](#第-13-章-project13-javaweb-filter-listener-模块知识点)
  - [13.1 过滤器 Filter](#131-过滤器-filter)
    - [13.1.1 Filter 属于 Servlet 规范](#1311-filter-属于-servlet-规范)
    - [13.1.2 Filter 开发步骤](#1312-filter-开发步骤)
    - [13.1.3 通配符配置 Filter](#1313-通配符配置-filter)
    - [13.1.4 过滤器链](#1314-过滤器链)
  - [13.2 监听器 Listener](#132-监听器-listener)

<!-- /code_chunk_output -->

# 第 13 章 project13-javaweb-filter-listener 模块知识点

## 13.1 过滤器 Filter

### 13.1.1 Filter 属于 Servlet 规范

### 13.1.2 Filter 开发步骤

- 新建类实现 Filter 接口。

- 实现其中的三个方法：init、doFilter、destroy。

- 配置 Filter，可以用 web.xml 文件，也可以使用注解 @WebFilter。

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns="http://xmlns.jcp.org/xml/ns/javaee"
          xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
          version="4.0">
      <servlet>
          <servlet-name>Dome1Servlet</servlet-name>
          <servlet-class>com.myxh.servlets.Demo1Servlet</servlet-class>
      </servlet>

      <servlet-mapping>
          <servlet-name>Dome1Servlet</servlet-name>
          <url-pattern>/demo1_servlet.do</url-pattern>
      </servlet-mapping>

      <filter>
          <filter-name>Dome1Filter</filter-name>
          <filter-class>com.myxh.filters.Dome1Filter</filter-class>
      </filter>

      <filter-mapping>
          <filter-name>Dome1Filter</filter-name>
          <url-pattern>/demo1_servlet.do</url-pattern>
      </filter-mapping>
  </web-app>
  ```

  ```java
  @WebFilter("/demo1_servlet.do")
  ```

### 13.1.3 通配符配置 Filter

Filter 在配置时，和 servlet 一样，也可以配置通配符，例如：

```java
@WebFilter("\*.do")
```

表示拦截所有以 .do 结尾的请求。

### 13.1.4 过滤器链

- 执行的顺序依次是： A1 B1 C1 dome3 service... C2 B2 A2

- 如果采取的是注解 @WebFilter 的方式进行配置，那么过滤器链的拦截顺序是按照全类名的先后顺序排序的。

- 如果采取的是 web.xml 的方式进行配置，那么过滤器链的拦截顺序是按照配置的先后顺序进行排序的。

## 13.2 监听器 Listener

- ServletContextListener：监听 ServletContext 对象的创建和销毁的过程。

- HttpSessionListener：监听 HttpSession 对象的创建和销毁的过程。

- ServletRequestListener：监听 ServletRequest 对象的创建和销毁的过程。

- ServletContextAttributeListener：监听 ServletContext 的保存作用域的改动(add、remove、replace)。

- HttpSessionAttributeListener：监听 HttpSession 的保存作用域的改动(add、remove、replace)。

- ServletRequestAttributeListener：监听 ServletRequest 的保存作用域的改动(add、remove、replace)。

- HttpSessionBindingListener：监听某个对象在 Session 域中的创建与移除。

- HttpSessionActivationListener：监听某个对象在 Session 域中的序列化和反序列化。
