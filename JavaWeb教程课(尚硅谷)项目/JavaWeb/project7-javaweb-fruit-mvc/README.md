<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project7-javaweb-fruit-mvc README

<!-- code_chunk_output -->

- [Java Web project7-javaweb-fruit-mvc README](#java-web-project7-javaweb-fruit-mvc-readme)
- [第 7 章 project7-javaweb-fruit-mvc 模块知识点](#第-7-章-project7-javaweb-fruit-mvc-模块知识点)
  - [7.1 实现水果库存系统的功能](#71-实现水果库存系统的功能)
    - [7.1.1 版本 2：project7-javaweb-fruit-mvc 模块](#711-版本-2project7-javaweb-fruit-mvc-模块)

<!-- /code_chunk_output -->

# 第 7 章 project7-javaweb-fruit-mvc 模块知识点

本文将对 project4-javaweb-fruit-thymeleaf、project5-javaweb-fruit-pagination、project6-javaweb-fruit-keyword、project7-javaweb-fruit-mvc、project8-javaweb-fruit-mvc-reflect、project9-javaweb-fruit-mvc-dispatcherServlet 和 project10-javaweb-fruit-mvc-controller 模块的知识点进行整理和讲解。我们将按照多个版本的演进迭代的顺序进行分析，以便更好地理解水果库存系统项目的发展过程。

## 7.1 实现水果库存系统的功能

### 7.1.1 版本 2：project7-javaweb-fruit-mvc 模块

1. 将一些列的请求都对应一个 Servlet，例如：IndexServlet、AddServlet、EditServlet、DeleteServlet、UpdateServlet 合并成 FruitServlet。

2. 通过一个 operate 的值来决定调用 FruitServlet 中的哪一个方法。

3. 使用 switch-case 语句根据 operate 的值来调用对应的方法。
