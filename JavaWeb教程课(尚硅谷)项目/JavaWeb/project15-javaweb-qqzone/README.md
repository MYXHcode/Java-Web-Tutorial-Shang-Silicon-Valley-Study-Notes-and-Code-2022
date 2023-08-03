<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project15-javaweb-qqzone README

<!-- code_chunk_output -->

- [Java Web project15-javaweb-qqzone README](#java-web-project15-javaweb-qqzone-readme)
- [第 15 章 project15-javaweb-qqzone 模块知识点](#第-15-章-project15-javaweb-qqzone-模块知识点)
  - [15.1 熟悉 QQZone 业务需求](#151-熟悉-qqzone-业务需求)
  - [15.2 数据库设计](#152-数据库设计)
  - [15.3 数据库的范式](#153-数据库的范式)
  - [15.4 数据库设计的范式和数据库的查询性能之间的平衡](#154-数据库设计的范式和数据库的查询性能之间的平衡)
  - [15.5 QQZone 实现登录功能出现的四个错误](#155-qqzone-实现登录功能出现的四个错误)

<!-- /code_chunk_output -->

# 第 15 章 project15-javaweb-qqzone 模块知识点

## 15.1 熟悉 QQZone 业务需求

1. 用户登录功能。

2. 主界面功能：

   - 用户登录成功，显示主界面。

   - 主界面左侧显示好友列表。

   - 主界面上端显示欢迎词，如果不是自己的空间，显示超链接返回自己的空间。

   - 主界面下端显示日志列表。

3. 查看日志详情功能：

   - 日志本身的信息：作者头像、昵称、日志标题、日志内容、日志日期。

   - 回复列表：回复者的头像、昵称、回复内容、回复日期。

   - 主人回复信息。

4. 删除日志。

5. 删除特定回复。

6. 删除特定主人回复。

7. 添加日志；添加回复；添加主人回复。

8. 点击左侧好友链接，进入好友的空间。

## 15.2 数据库设计

1. 抽取实体：用户登录信息、用户详情信息、日志、回贴、主人回复。

2. 分析实体的属性：

   - 用户登录信息：账号、密码、头像、昵称。

   - 用户详情信息：真实姓名、星座、血型、邮箱、手机号。

   - 日志：标题、内容、日期、作者。

   - 回复：内容、日期、作者、日志。

   - 主人回复：内容、日期、作者、回复。

3. 分析实体之间的关系：

   - 用户登录信息：用户详情信息 -> 1 : 1 Primary Key

   - 用户：日志 -> 1 : N

   - 日志：回复 -> 1 : N

   - 回复：主人回复 -> 1 : 1 Foreign Key

   - 用户：好友 -> M : N

## 15.3 数据库的范式

1. 第一范式：列不可再分。

2. 第二范式：一张表只表达一层含义（只描述一件事情）。

3. 第三范式：表中的每一列和主键都是直接依赖关系，而不是间接依赖关系。

## 15.4 数据库设计的范式和数据库的查询性能之间的平衡

- 数据库设计的范式和数据库的查询性能很多时候是相悖的，需要根据实际的业务情况做一个选择。

  - 查询频次不高的情况下，更倾向于提高数据库的设计范式，从而提高存储效率。

  - 查询频次较高的情况下，更倾向于牺牲数据库的规范度，降低数据库设计的范式，允许特定的冗余，从而提高查询的性能。

## 15.5 QQZone 实现登录功能出现的四个错误

1. 数据库 druid.properties 配置文件中的 URL 没修改，用的还是 url=jdbc:mysql:///my_fruit，应修改为 url=jdbc:mysql:///my_qqzone。

2. UserBasicDaoImpl 类的 getUserBasicList 方法中的数据库查询语句的 fid 应该指定别名为 id。

   ```java
   /**
    * 获取指定用户的所有好友列表
    */
   @Override
   public List<UserBasic> getUserBasicList(UserBasic userBasic)
   {
      String sql = "select fid as id from t_friend where uid = ?";

      return super.executeQuery(sql, userBasic.getId());
   }
   ```

3. metaData.getColumnName() 获取列名，metaData.getColumnLabel() 获取列的别名。

4. 无法将 com.myxh.qqzone.pojo.UserBasic 字段 com.myxx.qqzone.pojo.Topic.author 设置为 java.lang.Integer。

5. left.html 页面没有样式，同时数据也不展示。

- 原因：

  - 直接去请求的静态页面资源，那么并没有执行 super.processTemplate()，也就是 thymeleaf 没有起作用。

- 解决方法：

  - 新增 PageController 类，添加 page 方法， 目的是执行 super.processTemplate()方法，让 thymeleaf 生效：

    ```Java
    package com.myxh.ssm.springmvc;

    /**
     * @author MYXH
    * @date 2023/7/17
    */
    public class PageController
    {
       public String page(String page)
       {
          return page;    // 返回 frames/left
       }
    }
    ```
