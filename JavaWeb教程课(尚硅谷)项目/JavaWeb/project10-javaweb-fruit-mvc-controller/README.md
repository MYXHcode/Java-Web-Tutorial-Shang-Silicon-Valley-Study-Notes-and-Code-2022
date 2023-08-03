<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project10-javaweb-fruit-mvc-controller README

<!-- code_chunk_output -->

- [Java Web project10-javaweb-fruit-mvc-controller README](#java-web-project10-javaweb-fruit-mvc-controller-readme)
- [第 10 章 project10-javaweb-fruit-mvc-controller 模块知识点](#第-10-章-project10-javaweb-fruit-mvc-controller-模块知识点)
  - [10.1 实现水果库存系统的功能](#101-实现水果库存系统的功能)
    - [10.1.5 版本 5：project10-javaweb-fruit-mvc-controller 模块](#1015-版本-5project10-javaweb-fruit-mvc-controller-模块)

<!-- /code_chunk_output -->

# 第 10 章 project10-javaweb-fruit-mvc-controller 模块知识点

本文将对 project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination、project6-javaweb-fruit-keyword、project7-javaweb-fruit-mvc、project8-javaweb-fruit-mvc-reflect、project9-javaweb-fruit-mvc-dispatcherServlet 和 project10-javaweb-fruit-mvc-controller 模块的知识点进行整理和讲解。我们将按照多个版本的演进迭代的顺序进行分析，以便更好地理解水果库存系统项目的发展过程。

## 10.1 实现水果库存系统的功能

在这个章节中，我们将通过五个版本逐步实现水果库存系统的功能。

### 10.1.5 版本 5：project10-javaweb-fruit-mvc-controller 模块

1. 获取参数：获取即将要调用的方法的参数签名信息。

2. 执行方法：使用反射技术，调用 Controller Bean 中的方法。

3. 视图处理：根据方法返回的字符串，进行视图的处理。

经过以上五个版本的演进，我们实现了一个具备基本功能的水果库存系统。在这个过程中，我们采用了 MVC 设计模式，使用了 Thymeleaf 模板引擎，实现了分页和关键词搜索功能，并引入了反射技术和中央控制器类来优化代码结构。
