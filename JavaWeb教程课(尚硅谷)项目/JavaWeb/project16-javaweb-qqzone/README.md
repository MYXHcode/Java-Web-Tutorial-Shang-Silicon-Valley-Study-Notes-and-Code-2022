<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project16-javaweb-qqzone README

<!-- code_chunk_output -->

- [Java Web project16-javaweb-qqzone README](#java-web-project16-javaweb-qqzone-readme)
- [第 16 章 project16-javaweb-qqzone 模块知识点](#第-16-章-project16-javaweb-qqzone-模块知识点)
  - [16.1 显示登录者昵称](#161-显示登录者昵称)
  - [16.2 进入好友空间](#162-进入好友空间)
  - [16.3 日志详情页面实现](#163-日志详情页面实现)
  - [16.4 添加日志回复](#164-添加日志回复)
  - [16.5 删除日志回复](#165-删除日志回复)
  - [16.6 删除日志](#166-删除日志)

<!-- /code_chunk_output -->

# 第 16 章 project16-javaweb-qqzone 模块知识点

## 16.1 显示登录者昵称

top.html 页面显示登录者昵称，判断是否是自己的空间。

1. 显示登录者昵称：${session.userBasic.nickName}

2. 判断是否是自己的空间：${session.userBasic.id!=session.friend.id}，
   如果不是期望的效果，首先考虑将两者的 id 都显示出来。

## 16.2 进入好友空间

点击左侧的好友链接，进入好友空间。

1. 根据 id 获取指定 userBasic 信息，查询这个 userBasic 的 topicList，然后覆盖 friend 对应的 value。

2. main 页面应该展示 friend 中的 topicList，而不是 userBasic 中的 topicList。

3. 跳转后，在左侧（left.html）中显示整个 index 页面。

   - 问题：在 left 页面显示整个 index 布局。

   - 解决：给超链接添加 target 属性：target="\_top"，保证在顶层窗口显示整个 index 页面。

4. top.html 页面需要修改："欢迎进入 ${session.friend}"，
   top.html 页面的返回自己空间的超链接需要修改：

   ```html
   <a
     th:href="@{|/user.do?operate=friend&id=${session.userBasic.id}|}"
     target="\_top"
   ></a>
   ```

## 16.3 日志详情页面实现

1. 已知 topic 的 id，需要根据 topic 的 id 获取特定 topic。

2. 获取这个 topic 关联的所有的回复。

3. 如果某个回复有主人回复，需要查询出来。

   - 在 TopicController 中获取指定的 topic。

   - 具体这个 topic 中关联多少个 Reply，由 ReplyService 内部实现。

4. 获取到的 topic 中的 author 只有 id，那么需要在 topicService 的 getTopicAndAuthorById 方法中封装，在查询 topic 本身信息时，同时调用 userBasicService 中的获取 userBasic 方法，给 author 属性赋值。

5. 同理，在 reply 类中也有 author，而且这个 author 也是只有 id，那么也需要根据 id 查询得到 author，最后设置关联。

## 16.4 添加日志回复

## 16.5 删除日志回复

1. 如果回复有关联的主人回复，需要先删除主人回复。

2. 删除回复时的错误：

   无法删除或更新父行：外键约束失败 （my_qqzone.t_host_reply，CONSTRAINT FK_host_reply FOREING KEY（回复）REFERENCES t_reply（id））

   删除回复表记录时，发现删除失败，原因是在主人回复表中仍然有引用待删除的回复这条记录，如果需要删除主表数据，需要首先删除子表数据。

## 16.6 删除日志

1.  删除日志，首先需要考虑是否有关联的回复。
2.  删除回复，首先需要考虑是否有关联的主人回复。
3.  另外，如果不是自己的空间，则不能删除日志。
