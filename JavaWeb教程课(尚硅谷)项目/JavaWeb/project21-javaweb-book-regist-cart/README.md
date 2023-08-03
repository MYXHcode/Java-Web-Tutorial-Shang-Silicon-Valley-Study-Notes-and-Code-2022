<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project21-javaweb-book-regist-cart README

<!-- code_chunk_output -->

- [Java Web project21-javaweb-book-regist-cart README](#java-web-project21-javaweb-book-regist-cart-readme)
- [第 21 章 project21-javaweb-book-regist-cart 模块知识点](#第-21-章-project21-javaweb-book-regist-cart-模块知识点)
  - [21.1 在注册页面显示验证码](#211-在注册页面显示验证码)
  - [21.2 注册功能实现](#212-注册功能实现)
  - [21.3 注册页面表单验证](#213-注册页面表单验证)
  - [21.4 注册页面验证用户名是否重复](#214-注册页面验证用户名是否重复)
  - [21.5 原生的 Ajax](#215-原生的-ajax)
    - [21.5.1 Ajax （Asynchronous Javascript And XML 异步 JavaScript 和 XML）](#2151-ajax-asynchronous-javascript-and-xml-异步-javascript-和-xml)
    - [21.5.2 Ajax 开发步骤](#2152-ajax-开发步骤)

<!-- /code_chunk_output -->

# 第 21 章 project21-javaweb-book-regist-cart 模块知识点

## 21.1 在注册页面显示验证码

1. 添加 jar 包。

2. 在 web.xml 文件中配置 KaptchaServlet，以及配置相关的属性。

3. 在页面上访问这个 Servlet，然后这个 Servlet 实现两个功能：

   - 在页面上显示验证码图片。

   - 在 session 作用域中保存验证码信息，对应的 key 存储在 Constans 这个常量接口中。

4. 用户在注册页面中输入验证码发送给服务器，那么需要和 session 中保存的进行比较。

## 21.2 注册功能实现

用户注册功能实现。

## 21.3 注册页面表单验证

1. < form > 有一个事件 onsubmit。

   - onsubmit="return false"，那么表单点击提交按钮时不会提交。

   - onsubmit="return true"，那么表单点击提交按钮时会提交。

2. 获取文档中某一个节点的方式。

   ```javascript
   // DOM: Document Object Model 文档对象模型
   let nameText = document.getElementById("nameText");

   // BOM: Browser Object Model 浏览器对象模型
   let name = document.forms[0].name;
   ```

## 21.4 注册页面验证用户名是否重复

1. 第一步客户端发送异步请求；并绑定对结果处理的回调函数。

   ```html
   <input
     id="nameText"
     type="text"
     placeholder="请输入用户名"
     name="name"
     value="test"
     onblur="checkName(this.value)"
   />
   ```

- 定义 checkName 方法：

  - 创建 xmlHttpRequest 对象。

  - xmlHttpRequest 对象操作步骤：

    - open("GET", url, true)

    - onreadyStateChange 设置回调。

    - send() 发送请求。

  - 在回调函数中需要判断 xmlHttpRequest 对象的状态:
    - readyState 为 0 ~ 4 , status 为 200。

2. 第二步服务器端做校验，然后将校验结果响应给客户端。

## 21.5 原生的 Ajax

### 21.5.1 Ajax （Asynchronous Javascript And XML 异步 JavaScript 和 XML）

- 目的：用来发送异步的请求，然后当服务器给浏览器响应的时候再进行回调操作。

- 好处：提高用户体验，局部刷新，降低服务器负担，减轻浏览器压力，减轻网络带宽压力。

### 21.5.2 Ajax 开发步骤

1. 创建 XmlHttpRequest。

2. 调用 open 进行设置："GET" , URL , true。

3. 绑定状态改变时执行的回调函数：onreadystatechange。

4. 发送请求：send()。

5. 编写回调函数，在回调函数中：

- 只对 XMLHttpRequest 的 readystate 为 4 的时候响应。
- 只对 XMLHttpRequest 的 status 为 200 的时候响应。

> readystate 解释：
> 0：（未初始化）还没有调用 send()方法。
> 1：（载入）已调用 send()方法，正在发送请求。
> 2：（载入完成）send()方法执行完成，已经接收到全部响应内容。
> 3：（交互）正在解析响应内容。
> 4：（完成）响应内容解析完成，可以在客户端调用了。
