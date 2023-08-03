<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project22-javaweb-vue-axios-json README

<!-- code_chunk_output -->

- [Java Web project22-javaweb-vue-axios-json README](#java-web-project22-javaweb-vue-axios-json-readme)
- [第 22 章 project22-javaweb-vue-axios-json 模块知识点](#第-22-章-project22-javaweb-vue-axios-json-模块知识点)
  - [22.1 Vue 入门](#221-vue-入门)
    - [22.1.1 Vue 声明式渲染](#2211-vue-声明式渲染)
    - [22.1.2 Vue 绑定元素属性](#2212-vue-绑定元素属性)
    - [22.1.3 Vue 双向数据绑定](#2213-vue-双向数据绑定)
    - [22.1.4 Vue 条件渲染](#2214-vue-条件渲染)
    - [22.1.5 Vue 列表渲染](#2215-vue-列表渲染)
    - [22.1.6 Vue 事件驱动](#2216-vue-事件驱动)
    - [22.1.7 侦听属性](#2217-侦听属性)
    - [22.1.8 Vue 对象的生命周期](#2218-vue-对象的生命周期)
    - [22.1.9 其他](#2219-其他)
  - [22.2 Axios 入门](#222-axios-入门)
    - [22.2.1 Axios 概述](#2221-axios-概述)
    - [22.2.2 Axios 执行 Ajax 操作的步骤](#2222-axios-执行-ajax-操作的步骤)

<!-- /code_chunk_output -->

# 第 22 章 project22-javaweb-vue-axios-json 模块知识点

## 22.1 Vue 入门

### 22.1.1 Vue 声明式渲染

- {{}} 相当于 innerText。

### 22.1.2 Vue 绑定元素属性

- v-bind:attr 绑定属性值。

  - 例如，v-bind:value 绑定 value 值，简写为 :value。

### 22.1.3 Vue 双向数据绑定

- v-model 双向绑定。

  - 例如，v-model:value 双向绑定 value 值，简写为 v-model。

### 22.1.4 Vue 条件渲染

- v-if, v-else, v-show

  - v-if 和 v-else 之间不能有其他的节点。

  - v-show 是通过样式表 display 来控制节点是否显示。

### 22.1.5 Vue 列表渲染

- v-for 迭代

  - 例如，v-for="fruit in fruitList" 迭代 fruitList

### 22.1.6 Vue 事件驱动

- v-on 绑定事件。

### 22.1.7 侦听属性

- watch 表示侦听属性。

### 22.1.8 Vue 对象的生命周期

- Vue 对象的生命周期。

### 22.1.9 其他

- trim 去除首尾空格。

- split() 分割字符串。

- join() 连接字符串。

## 22.2 Axios 入门

### 22.2.1 Axios 概述

Axios 是 Ajax 的一个框架，可以简化 Ajax 操作。

### 22.2.2 Axios 执行 Ajax 操作的步骤

1.  添加并引入 axios 的 js 文件。

2.  客户端向服务器端异步发送普通参数值。

    - 基本格式：axios().then().catch()

    - 示例：

      ```javascript
      axios({
        method: "POST",
        url: "axios.do",
        params: {
          name: "MYXH",
          password: "520.ILY!",
        },
      })
        .then(function (value) {
          // 成功响应时执行的回调, value.data 可以获取到服务器响应内容
          console.log(value);
        })
        .catch(function (reason) {
          // 有异常时执行的回调, reason.response.data 可以获取到响应的内容, reason.message 和 reason.stack 可以查看错误的信息
          console.log(reason);
        });
      ```

3.  客户端向服务器端异步发送 JSON 格式的数据。

    - 什么是 JSON？

      - JSON 是一种数据格式。

      - XML 也是一种数据格式

      - XML 格式表示两个学生信息的代码如下：

        ```xml
          <students>
            <student id="001">
              <name>Tom</name>
              <age>19</age>
            </student>

            <student id="002">
              <name>Jerry</name>
              <age>18</age>
            </student>
          </students>
        ```

      - JSON 格式表示两个学生信息的代码如下：

        ```json
        [
          { "id": "001", "name":"Tom" , age": 19 },
          { "id": "002", "name":"Jerry" , "age": 18 }
        ]
        ```

    - JSON 表达数据更简洁，更能够节约网络带宽。

    - 客户端异步发送 JSON 格式的数据给服务器端：

      - 客户端中 params: 需要修改成 data:

      - 服务器获取参数值不再是 request.getParameter()，而是：

        ```java
        BufferedReader bufferedReader = request.getReader();

        StringBuilder stringBuilder = new StringBuilder();

        String str;

        while ((str = bufferedReader.readLine()) != null)
        {
            stringBuilder.append(str);
        }

        str = stringBuilder.toString();
        ```

      - str 的内容如下：
        {"name":"MYXH","password":"520.ILY!"}

4.  服务器端给客户端响应 JSON 格式的字符串，然后客户端需要将 javascript 字符串转化成 javascript Object。
