<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project20-javaweb-cookie-kaptcha-js README

<!-- code_chunk_output -->

- [Java Web project20-javaweb-cookie-kaptcha-js README](#java-web-project20-javaweb-cookie-kaptcha-js-readme)
- [第 20 章 project20-javaweb-cookie-kaptcha-js 模块知识点](#第-20-章-project20-javaweb-cookie-kaptcha-js-模块知识点)
  - [20.1 Cookie](#201-cookie)
  - [20.2 Kaptcha 验证码](#202-kaptcha-验证码)
  - [20.3 JavaScript 中的正则表达式 regularular Expression](#203-javascript-中的正则表达式-regularular-expression)
    - [20.3.1 正则表达式的使用三个步骤](#2031-正则表达式的使用三个步骤)
    - [20.3.2 元字符](#2032-元字符)
    - [20.3.3 [] 表示集合](#2033--表示集合)
    - [20.3.4 表示出现的次数](#2034-表示出现的次数)

<!-- /code_chunk_output -->

# 第 20 章 project20-javaweb-cookie-kaptcha-js 模块知识点

## 20.1 Cookie

1. 创建一个 Cookie 对象。

   ```java
   // 1. 创建一个 Cookie 对象
   Cookie cookie = new Cookie("name", "MYXH");
   ```

2. 在浏览器端保存 Cookie。

   ```java
   // 2. 将这个 Cookie 对象保存到浏览器端
   response.addCookie(cookie);
   ```

3. 服务器端内部转发。

   ```java
   // 3. 服务器端内部转发
   request.getRequestDispatcher("cookie_servlet1.html").forward(request, response);
   ```

4. 设置 Cookie 的有效时长。

   - cookie.setMaxAge(60)：设置 cookie 的有效时长是 60 秒。

   - cookie.setDomain(pattern)：设置 cookie 共享范围,指定哪些域名下的服务器可以访问这个 cookie。

   - cookie.setPath(uri)：设置 cookie 生效的路径,指定请求访问的路径才会包含这个 cookie。

5. Cookie 的应用。

   - 记住用户名和密码，实现 10 天免登录：setMaxAge(60 \* 60 \* 24 \* 10)

## 20.2 Kaptcha 验证码

1. 为什么需要验证码？

2. kaptcha 如何使用？

   - 添加 jar 包。

   - 在 web.xml 文件中注册 KaptchaServlet，并设置验证码图片的相关属性。

   - 在 html 页面上编写一个 img 标签，然后设置 src 等于 KaptchaServlet 对应的 url-pattern。

3. kaptcha 验证码图片的各个属性在常量接口 Constants 中。

4. KaptchaServlet 在生成验证码图片时，会同时将验证码信息保存到 session 中。

   - 因此，在注册请求时，首先将用户文本框中输入的验证码值和 session 中保存的值进行比较，若相等，则进行注册。

## 20.3 JavaScript 中的正则表达式 regularular Expression

### 20.3.1 正则表达式的使用三个步骤

1. 定义正则表达式对象。

   1.1 正则表达式定义有两个方式：

   1.1.1 对象形式

   ```javascript
   let regularular = new regularExp("abc");
   ```

   1.1.2 直接量形式

   ```javascript
   let regular = /abc/;
   ```

   1.2 匹配模式：

   - g：全局匹配。

   - i：忽略大小写匹配。

   - m：多行匹配。

   - gim 这三个可以组合使用，不区分先后顺序。

     - 例如：

       ```javascript
       let regular = /abc/gim;
       let regular = new regularExp("abc", "gim");
       ```

2. 定义待校验的字符串。

3. 校验。

### 20.3.2 元字符

> . 、 \w 、 \W 、 \s 、 \S 、 \d 、 \D 、 \b 、 ^ 、 $

### 20.3.3 [] 表示集合

- [abc] 表示 a 或者 b 或者 c。

- [^abc] 表示取反，只要不是 a，不是 b，不是 c 就匹配。

- [a-c] 表示 a 到 c 这个范围匹配。

### 20.3.4 表示出现的次数

- \* 表示多次（0 ~ n）。

- \+ 表示至少一次（> = 1）。

- ? 表示最多一次（0 ~ 1）。

- {n} 表示出现 n 次。

- {n,} 表示出现 n 次或者多次。

- {n,m} 表示出现 n 到 m 次。
