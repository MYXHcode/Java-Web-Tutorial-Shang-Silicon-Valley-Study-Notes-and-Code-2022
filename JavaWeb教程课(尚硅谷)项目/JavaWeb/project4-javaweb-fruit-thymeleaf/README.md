<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project4-javaweb-fruit-thymeleaf README

<!-- code_chunk_output -->

- [Java Web project4-javaweb-fruit-thymeleaf README](#java-web-project4-javaweb-fruit-thymeleaf-readme)
- [第 4 章 project4-javaweb-fruit-thymeleaf 模块知识点](#第-4-章-project4-javaweb-fruit-thymeleaf-模块知识点)
  - [4.1 保存作用域](#41-保存作用域)
  - [4.2 路径问题](#42-路径问题)
  - [4.3 实现水果库存系统的功能](#43-实现水果库存系统的功能)
    - [4.3.1 版本 1：project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination 和 project6-javaweb-fruit-keyword 模块](#431-版本-1project4-javaweb-fruit-thymeleaf-project5-javaweb-fruit-pagination-和-project6-javaweb-fruit-keyword-模块)

<!-- /code_chunk_output -->

# 第 4 章 project4-javaweb-fruit-thymeleaf 模块知识点

本文将对 project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination、project6-javaweb-fruit-keyword、project7-javaweb-fruit-mvc、project8-javaweb-fruit-mvc-reflect、project9-javaweb-fruit-mvc-dispatcherServlet 和 project10-javaweb-fruit-mvc-controller 模块的知识点进行整理和讲解。我们将按照多个版本的演进迭代的顺序进行分析，以便更好地理解水果库存系统项目的发展过程。

## 4.1 保存作用域

- 原始情况下，保存作用域可以认为有四个：
  - page（页面级别，现在几乎不用）
  - request（一次请求响应范围有效）
  - session（一次会话范围有效）
  - application（一次应用程序范围有效）

## 4.2 路径问题

- 相对路径

- 绝对路径

## 4.3 实现水果库存系统的功能

在这个章节中，我们将通过五个版本逐步实现水果库存系统的功能。

### 4.3.1 版本 1：project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination 和 project6-javaweb-fruit-keyword 模块

1. 最初的做法是：一个请求对应一个 Servlet，这样存在的问题是 Servlet 太多了。

2. 实现水果库存系统的基本功能，使用 Thymeleaf 模板引擎进行页面渲染。

3. 添加分页功能，使得页面可以按照设定的每页显示数量进行展示。

4. 实现关键词搜索功能，方便用户根据关键词快速查找水果。
