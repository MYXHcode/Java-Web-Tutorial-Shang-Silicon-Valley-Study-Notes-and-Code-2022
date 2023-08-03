<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project8_javaweb_fruit_mvc_reflect README

<!-- code_chunk_output -->

- [Java Web project8_javaweb_fruit_mvc_reflect README](#java-web-project8_javaweb_fruit_mvc_reflect-readme)
- [第 8 章 project8_javaweb_fruit_mvc_reflect 模块知识点](#第-8-章-project8_javaweb_fruit_mvc_reflect-模块知识点)
  - [8.1 实现水果库存系统的功能](#81-实现水果库存系统的功能)
    - [8.1.1 版本 3：project8-javaweb-fruit-mvc-reflect 模块](#811-版本-3project8-javaweb-fruit-mvc-reflect-模块)

<!-- /code_chunk_output -->

# 第 8 章 project8_javaweb_fruit_mvc_reflect 模块知识点

本文将对 project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination、project6-javaweb-fruit-keyword、project7-javaweb-fruit-mvc、project8-javaweb-fruit-mvc-reflect、project9-javaweb-fruit-mvc-dispatcherServlet 和 project10-javaweb-fruit-mvc-controller 模块的知识点进行整理和讲解。我们将按照多个版本的演进迭代的顺序进行分析，以便更好地理解水果库存系统项目的发展过程。

## 8.1 实现水果库存系统的功能

### 8.1.1 版本 3：project8-javaweb-fruit-mvc-reflect 模块

1. 为了解决 Servlet 中充斥着大量的 switch-case 问题，采用反射技术。

2. 规定 operate 的值和方法名一致，接收到 operate 的值是什么就表明我们需要调用对应的方法进行响应。

3. 如果找不到对应的方法，则抛出异常。
