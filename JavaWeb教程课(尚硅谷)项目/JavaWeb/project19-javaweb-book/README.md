<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project19-javaweb-book README

<!-- code_chunk_output -->

- [Java Web project19-javaweb-book README](#java-web-project19-javaweb-book-readme)
- [第 19 章 project19-javaweb-book 模块知识点](#第-19-章-project19-javaweb-book-模块知识点)
  - [19.1 显示 index 主页面](#191-显示-index-主页面)
  - [19.2 显示欢迎词和购物车数量](#192-显示欢迎词和购物车数量)
  - [19.3 添加到购物车的按钮](#193-添加到购物车的按钮)
  - [19.4 显示购物车详情](#194-显示购物车详情)
  - [19.5 结账功能](#195-结账功能)
  - [19.6 订单信息中的订单数量](#196-订单信息中的订单数量)
  - [19.7 编辑购物车](#197-编辑购物车)
  - [19.8 关于金额的精度问题](#198-关于金额的精度问题)
  - [19.9 过滤器判断是否为合法用户](#199-过滤器判断是否为合法用户)

<!-- /code_chunk_output -->

# 第 19 章 project19-javaweb-book 模块知识点

## 19.1 显示 index 主页面

- 新建 BookDAO 类、BookDAOImpl 类：getBookList() 方法。

- 新建 BookService 类、BookServiceImpl 类：getBookList() 方法。

- 新建 BookController 类：index() 方法。

- 编辑 index.html。

## 19.2 显示欢迎词和购物车数量

在首页登录成功之后，显示欢迎词和购物车数量。

## 19.3 添加到购物车的按钮

点击具体图书的添加按钮，添加到购物车。

## 19.4 显示购物车详情

显示购物车详情。

## 19.5 结账功能

1. 订单表添加 1 条记录。

2. 订单项表添加对应的多条记录。

3. 购物车项表中需要删除对应的多条记录。

## 19.6 订单信息中的订单数量

关于订单信息中的订单数量的问题。

## 19.7 编辑购物车

编辑购物车。

## 19.8 关于金额的精度问题

关于金额的精度问题：使用 BigDecimal 类型。

## 19.9 过滤器判断是否为合法用户

- 解决方法：新建 SessionFilter ， 用来判断 session 中是否保存了 currentUser。

- 如果没有 currentUser，表明当前不是一个登录合法的用户，应该跳转到登录页面让其登录。

- 现在添加了过滤器之后，出现了如下错误：

  - localhost 将您重定向的次数过多。（ERR_TOO_MANY_REDIRECTS）

    - 尝试清除 Cookie。

    - 设置过滤器白名单。

      ```java
      @WebFilter(urlPatterns = {"*.do", "*.html"},
              initParams = {
                      @WebInitParam(name = "whiteList",
                              value = "/project19_javaweb_book/page.do?operate=page&page=user/login,/project19_javaweb_book/user.do?null")
              }
      )
      ```
