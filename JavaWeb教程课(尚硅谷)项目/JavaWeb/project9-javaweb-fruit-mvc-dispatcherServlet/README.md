<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project9-javaweb-fruit-mvc-dispatcherServlet README

<!-- code_chunk_output -->

- [Java Web project9-javaweb-fruit-mvc-dispatcherServlet README](#java-web-project9-javaweb-fruit-mvc-dispatcherservlet-readme)
- [第 9 章 project9-javaweb-fruit-mvc-dispatcherServlet 模块知识点](#第-9-章-project9-javaweb-fruit-mvc-dispatcherservlet-模块知识点)
  - [9.1 实现水果库存系统的功能](#91-实现水果库存系统的功能)
    - [9.1.1 版本 4：project9-javaweb-fruit-mvc-dispatcherServlet 模块](#911-版本-4project9-javaweb-fruit-mvc-dispatcherservlet-模块)

<!-- /code_chunk_output -->

# 第 9 章 project9-javaweb-fruit-mvc-dispatcherServlet 模块知识点

本文将对 project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination、project6-javaweb-fruit-keyword、project7-javaweb-fruit-mvc、project8-javaweb-fruit-mvc-reflect、project9-javaweb-fruit-mvc-dispatcherServlet 和 project10-javaweb-fruit-mvc-controller 模块的知识点进行整理和讲解。我们将按照多个版本的演进迭代的顺序进行分析，以便更好地理解水果库存系统项目的发展过程。

## 9.1 实现水果库存系统的功能

### 9.1.1 版本 4：project9-javaweb-fruit-mvc-dispatcherServlet 模块

1. 设计中央控制器类：DispatcherServlet，用来解决反射技术代码重复问题。

2. DispatcherServlet 的工作分为两大部分：

   - 根据 URL 定位到能够处理这个请求的 Controller 组件。

     - 从 URL 中提取 servletPath：/fruit.do -> fruit

     - 根据 fruit 找到对应的组件：FruitController，这个对应的依据存储在 applicationContext.xml 中，通过 DOM 技术解析 XML 文件，在中央控制器中形成一个 beanMap 容器，用来存放所有的 Controller 组件。

     - 根据获取到的 operate 的值定位到我们 FruitController 中需要调用的方法。

   - 调用 Controller 组件中的方法。

     - 获取参数：

       获取即将要调用的方法的参数签名信息：

       ```java
       Parameter[] parameters = method.getParameters();
       ```

       通过 parameter.getName() 获取参数的名称；

       准备了 Object[] parameterValues 这个数组用来存放对应参数的参数值；

       另外，我们需要考虑参数的类型问题，需要做类型转化的工作。通过 parameter.getType() 获取参数的类型。

     - 执行方法：

       ```java
         Object returnObj = method.invoke(controllerBean , parameterValues);
       ```

     - 视图处理：

       ```java
         String returnStr = (String)returnObj;

         if(returnStr.startWith("redirect:"))
         {
             // ...
         }else if(/* ... */)
         {
             //...
         }
       ```

3. 解析 applicationContext.xml 文件，形成一个 beanMap 容器，用来存放所有的 Controller 组件。
