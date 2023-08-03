<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project2-javaweb-servlet README

<!-- code_chunk_output -->

- [Java Web project2-javaweb-servlet README](#java-web-project2-javaweb-servlet-readme)
- [第 2 章 project2-javaweb-servlet 模块知识点](#第-2-章-project2-javaweb-servlet-模块知识点)
  - [2.1 Servlet 的继承关系](#21-servlet-的继承关系)
    - [2.1.1 继承关系](#211-继承关系)
    - [2.1.2 相关方法](#212-相关方法)
    - [2.1.3 小结](#213-小结)
  - [2.2 Servlet 的生命周期](#22-servlet-的生命周期)
    - [2.2.1 生命周期](#221-生命周期)
    - [2.2.2 默认情况](#222-默认情况)
    - [2.2.3 通过案例发现](#223-通过案例发现)
    - [2.2.4 Servlet 的初始化时机](#224-servlet-的初始化时机)
    - [2.2.5 Servlet 在容器中是单例的、线程不安全的](#225-servlet-在容器中是单例的-线程不安全的)
  - [2.3 Http 协议](#23-http-协议)
    - [2.3.1 Http 称之为超文本传输协议](#231-http-称之为超文本传输协议)
    - [2.3.2 Http 是无状态的](#232-http-是无状态的)
    - [2.3.3 Http 请求响应包含两个部分：请求和响应](#233-http-请求响应包含两个部分请求和响应)
  - [2.4 会话](#24-会话)
    - [2.4.1 Http 是无状态的](#241-http-是无状态的)
    - [2.4.2 会话跟踪技术](#242-会话跟踪技术)
    - [2.4.3 session 保存作用域](#243-session-保存作用域)
  - [2.5 服务器内部转发以及客户端重定向](#25-服务器内部转发以及客户端重定向)
    - [2.5.1 服务器内部转发](#251-服务器内部转发)
    - [2.5.2 客户端重定向](#252-客户端重定向)

<!-- /code_chunk_output -->

# 第 2 章 project2-javaweb-servlet 模块知识点

## 2.1 Servlet 的继承关系

Servlet 的继承关系，重点关注的是服务方法 service()。

### 2.1.1 继承关系

- jakarta.servlet.Servlet 接口

  - jakarta.servlet.GenericServlet 抽象类

    - jakarta.servlet.http.HttpServlet 抽象子类

### 2.1.2 相关方法

- jakarta.servlet.Servlet 接口

  - void init(config) 初始化方法

  - void service(request,response) 服务方法

  - void destory() 销毁方法

- jakarta.servlet.GenericServlet 抽象类

  - void service(request,response) 抽象方法

- jakarta.servlet.http.HttpServlet 抽象子类

  - void service(request,response) 不是抽象方法

```java
// 1. 获取请求的方式
String method = req.getMethod();

// 2. 各种 if 判断，根据请求方式不同，决定去调用不同的 do 方法
if (method.equals("GET"))
{
   this.doGet(req,resp);
}
else if (method.equals("HEAD"))
{
   this.doHead(req, resp);
}
else if (method.equals("POST"))
{
   this.doPost(req, resp);
}
else if (method.equals("PUT"))
{
   this.doPut(req, resp);
}

// 3. 在 HttpServlet 这个抽象类中，do 方法都类似
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
{
   String protocol = req.getProtocol();
   String msg = lStrings.getString("http.method_get_not_supported");

   if (protocol.endsWith("1.1"))
   {
      resp.sendError(405, msg);
   }
   else
   {
      resp.sendError(400, msg);
   }
}
```

### 2.1.3 小结

- 继承关系： HttpServlet -> GenericServlet -> Servlet。

- Servlet 中的核心方法： init()， service()， destroy()。

- 服务方法： 当有请求过来时，service 方法会自动响应（其实是 Tomcat 容器调用的）。
  在 HttpServlet 中会去分析请求的方式：到底是 get、post、head 还是 delete 等，然后再决定调用的是哪个 do 开头的方法。
  那么在 HttpServlet 中这些 do 方法默认都是 405 的实现风格，要子类去实现对应的方法，否则默认会报 405 错误。
  因此，在新建 Servlet 时，才会去考虑请求方法，从而决定重写哪个 do 方法。

## 2.2 Servlet 的生命周期

### 2.2.1 生命周期

- 从出生到死亡的过程就是生命周期。
- 对应 Servlet 中的三个方法：init()， service()， destroy()。

### 2.2.2 默认情况

- 第一次接收请求时，这个 Servlet 会进行实例化(调用构造方法)、初始化(调用 init())、然后服务(调用 service())。

- 从第二次请求开始，每一次都是服务。
  当容器关闭时，其中的所有的 servlet 实例会被销毁，调用销毁方法。

### 2.2.3 通过案例发现

- Servlet 实例 Tomcat 只会创建一个，所有的请求都是这个实例去响应。

- 默认情况下，第一次请求时，Tomcat 才会去实例化，初始化，然后再服务。
  这样的好处是什么？提高系统的启动速度。
  这样的缺点是什么？第一次请求时，耗时较长。

- 结论： 如果需要提高系统的启动速度，使用默认设置。
  如果需要提高响应速度，我们应该设置 Servlet 的初始化时机。

### 2.2.4 Servlet 的初始化时机

- 默认是第一次接收请求时，实例化，初始化。

- 我们可以通过 < load-on-startup > 来设置 servlet 启动的先后顺序，数字越小，启动越靠前，最小值 0。

### 2.2.5 Servlet 在容器中是单例的、线程不安全的

- 单例：所有的请求都是同一个实例去响应。

- 线程不安全：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断。但是在中间某个时机，另一个线程改变了这个成员变量的值，从而导致第一个线程的执行路径发生了变化。

- servlet 是线程不安全的，尽量的不要在 servlet 中定义成员变量。如果不得不定义成员变量，那么不要去：
  ① 不要去修改成员变量的值。
  ② 不要去根据成员变量的值做一些逻辑判断。

## 2.3 Http 协议

### 2.3.1 Http 称之为超文本传输协议

### 2.3.2 Http 是无状态的

### 2.3.3 Http 请求响应包含两个部分：请求和响应

- 请求包含三个部分

  - 请求行

    - 请求的方式

      - 请求的 URL

      - 请求的协议（一般都是 HTTP1.1）

  - 请求消息头

    - 请求消息头中包含了很多客户端需要告诉服务器的信息，比如：浏览器型号、版本、能接收的内容的类型、发送的内容的类型、内容的长度等。

  - 请求主体

    - get 方式，没有请求体，但是有一个 queryString。

    - post 方式，有请求体，form data。

    - json 格式，有请求体，request payload。

- 响应包含三个部分

  - 响应行

    - 协议

    - 响应状态码(200)

    - 响应状态(ok)

  - 响应头

    - 响应头中包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型、编码、内容长度等）。

  - 响应体

    - 响应的实际内容（比如请求 add.html 页面时，响应的内容就是< html > < head > < body > < form...）。

  > HTTP 200：正常响应。
  > HTTP 404：找不到对应的资源。
  > HTTP 405：请求方式不支持。
  > HTTP 500：服务器内部错误。

## 2.4 会话

### 2.4.1 Http 是无状态的

- HTTP 无状态

  - 服务器无法判断这两次请求是同一个客户端发过来的，还是不同的客户端发过来的。

- 无状态带来的问题

  - 第一次请求是添加商品到购物车，第二次请求是结账；如果这两次请求服务器无法区分是同一个用户的，那么就会导致混乱。

- 通过会话跟踪技术来解决无状态的问题。

### 2.4.2 会话跟踪技术

- 客户端第一次发请求给服务器，服务器获取 session，获取不到，则创建新的，然后响应给客户端。

- 下次客户端给服务器发请求时，会把 sessionID 带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次某次请求是同一个客户端，从而能够区分开客户端。

- 常用的 API

  - request.getSession()：获取当前的会话，没有则创建一个新的会话。

  - request.getSession(true)：效果和不带参数相同。

  - request.getSession(false)：获取当前会话，没有则返回 null，不会创建新的。

  - session.getId()：获取 sessionID。

  - session.isNew()：判断当前 session 是否是新的。

  - session.getMaxInactiveInterval() / session.setMaxInactiveInterval()：session 的非激活间隔时长，默认 1800 秒。

  - session.invalidate()：强制性让会话立即失效。

### 2.4.3 session 保存作用域

- session 保存作用域是和具体的某一个 session 对应的。

- 常用的 API

  - void session.setAttribute(k,v)

  - Object session.getAttribute(k)

  - void removeAttribute(k)

## 2.5 服务器内部转发以及客户端重定向

### 2.5.1 服务器内部转发

- request.getRequestDispatcher("...").forward(request,response);

- 一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的。

- 浏览器地址栏没有变化。

### 2.5.2 客户端重定向

- response.sendRedirect("...");

- 两次请求响应的过程，客户端肯定知道请求 URL 有变化。

- 浏览器地址栏有变化。
