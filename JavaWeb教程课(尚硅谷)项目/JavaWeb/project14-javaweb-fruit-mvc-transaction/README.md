<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project14-javaweb-fruit-mvc-transaction README

<!-- code_chunk_output -->

- [Java Web project14-javaweb-fruit-mvc-transaction README](#java-web-project14-javaweb-fruit-mvc-transaction-readme)
- [第 14 章 project14-javaweb-fruit-mvc-transaction 模块知识点](#第-14-章-project14-javaweb-fruit-mvc-transaction-模块知识点)
  - [14.1 通过 CharacterEncodingFilter 过滤器设置 Servlet 字符编码](#141-通过-characterencodingfilter-过滤器设置-servlet-字符编码)
  - [14.2 通过 OpenSessionInViewFilter 过滤器实现事务管理机制，保证线程安全](#142-通过-opensessioninviewfilter-过滤器实现事务管理机制保证线程安全)
  - [14.3 通过 ContextLoaderListener 监听器在上下文启动的时候去创建 IOC 容器](#143-通过-contextloaderlistener-监听器在上下文启动的时候去创建-ioc-容器)
  - [14.4 事务管理](#144-事务管理)
    - [14.4.1 实现事务管理涉及到的组件](#1441-实现事务管理涉及到的组件)
    - [14.4.2 ThreadLocal 源码分析](#1442-threadlocal-源码分析)

<!-- /code_chunk_output -->

# 第 14 章 project14-javaweb-fruit-mvc-transaction 模块知识点

## 14.1 通过 CharacterEncodingFilter 过滤器设置 Servlet 字符编码

## 14.2 通过 OpenSessionInViewFilter 过滤器实现事务管理机制，保证线程安全

## 14.3 通过 ContextLoaderListener 监听器在上下文启动的时候去创建 IOC 容器

## 14.4 事务管理

### 14.4.1 实现事务管理涉及到的组件

- OpenSessionInViewFilter

- TransactionManager

- ThreadLocal

- ConnectionUtil

- BaseDAO

- JdbcUtils

### 14.4.2 ThreadLocal 源码分析

- get()、set(object)

- ThreadLocal 称之为本地线程，可以通过 set 方法在当前线程上存储数据、通过 get 方法在当前线程上获取数据。

- set 方法源码分析

  ```java
  public void set(T value)
  {
      // 获取当前的线程
      Thread t = Thread.currentThread();

      //每一个线程都维护各自的一个容器(ThreadLocalMap)
      ThreadLocalMap map = getMap(t);

      if (map != null)
      {
          //这里的 key 对应的是 ThreadLocal, 因为我们的组件中需要传输(共享)的对象可能会有多个, 不止 Connection
          map.set(this, value);
      }
      else
      {
          //默认情况下 map 是没有初始化的, 那么第一次往其中添加数据时, 会执行初始化
          createMap(t, value);
      }
  }
  ```

- get 方法源码分析

  ```java
  public T get()
  {
      // 获取当前的线程
      Thread t = Thread.currentThread();

      // 获取和这个线程相关的 ThreadLocalMap(也就是工作纽带的集合)
      ThreadLocalMap map = getMap(t);

      if (map != null)
      {
          // this 指的是 ThreadLocal 对象, 通过它才能知道是哪一个工作纽带
          ThreadLocalMap.Entry e = map.getEntry(this);

          if (e != null)
          {
              @SuppressWarnings("unchecked")
              // entry.value 就可以获取到工具箱了
              T result = (T)e.value;

              return result;
          }
      }

      return setInitialValue();
  }
  ```
