<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

# Java Web project11-javaweb-fruit-mvc-service-ioc README

<!-- code_chunk_output -->

- [Java Web project11-javaweb-fruit-mvc-service-ioc README](#java-web-project11-javaweb-fruit-mvc-service-ioc-readme)
- [第 11 章 project11-javaweb-fruit-mvc-service-ioc 模块知识点](#第-11-章-project11-javaweb-fruit-mvc-service-ioc-模块知识点)
  - [11.1 业务层](#111-业务层)
    - [11.1.1 Model1 和 Model2](#1111-model1-和-model2)
    - [11.1.2 区分业务对象和数据访问对象](#1112-区分业务对象和数据访问对象)
    - [11.1.3 在库存系统中添加业务层组件](#1113-在库存系统中添加业务层组件)
  - [11.2 IOC(Inversion of Control) 控制反转](#112-iocinversion-of-control-控制反转)
    - [11.2.1 耦合、依赖](#1121-耦合-依赖)
    - [11.2.2 IOC(Inversion of Control) 控制反转、 DI(Dependency Injection) 依赖注入](#1122-iocinversion-of-control-控制反转--didependency-injection-依赖注入)

<!-- /code_chunk_output -->

# 第 11 章 project11-javaweb-fruit-mvc-service-ioc 模块知识点

## 11.1 业务层

### 11.1.1 Model1 和 Model2

- MVC：Model（模型）、View（视图）、Controller（控制器）。

  - 视图层（View）：用于做数据展示以及和用户交互的一个界面。

  - 控制层（Controller）：能够接受客户端的请求，具体的业务功能还是需要借助于模型组件来完成。

  - 模型层（Model）：模型分为很多种，有比较简单的 POJO(Plain Ordinary Java Object)、VO(Value Object)，有 DAO(Data Transform Object) 数据访问层组件，有 BO(Business Object) 业务模型组件，有 DTO(Data Transfer Object) 数据传输对象。

    - POJO(Plain Ordinary Java Object)、VO(Value Object)：值对象。

    - DAO(Data Transform Object)： 数据访问对象。

    - BO(Business Object)： 业务对象。

    - DTO(Data Transfer Object)：数据传输对象。

### 11.1.2 区分业务对象和数据访问对象

- DAO 中的方法都是单精度方法或细粒度方法。

  - 什么叫单精度？
    一个方法只考虑一个操作，比如添加是 insert 操作、查询是 select 操作等等。

- BO 中的方法属于业务方法，而实际的业务是比较复杂的，因此业务方法的粒度是比较粗的。

  - 注册这个功能属于业务功能，也就是说注册这个方法属于业务方法。

  - 那么这个业务方法中包含了多个 DAO 方法，也就是说注册这个业务功能需要通过多个 DAO 方法的组合调用，从而完成注册功能的开发。

    - 注册业务方法：

      1. 检查用户名是否已经被注册，DAO 中的 select 操作。

      2. 向用户表新增一条新用户记录，DAO 中的 insert 操作。

      3. 向用户积分表新增一条记录（新用户默认初始化积分 100 分），DAO 中的 insert 操作。

      4. 向系统消息表新增一条记录（某新用户注册后，需要根据通讯录信息向他的联系人推送消息），DAO 中的 insert 操作。

      5. 向系统日志表新增一条记录（某用户在某 IP 在某年某月某日某时某分某秒某毫秒注册），DAO 中的 insert 操作。

### 11.1.3 在库存系统中添加业务层组件

## 11.2 IOC(Inversion of Control) 控制反转

### 11.2.1 耦合、依赖

- 在软件系统中，层与层之间是存在依赖的，也称之为耦合。

- 系统架构设计的一个原则是：高内聚低耦合。

- 层内部的组成应该是高度聚合的，而层与层之间的关系应该是低耦合的，最理想的情况是零耦合（就是没有耦合）。

### 11.2.2 IOC(Inversion of Control) 控制反转、 DI(Dependency Injection) 依赖注入

- 控制反转

  - 之前在 Controller 中创建 service 对象时。

    ```java
    FruitService fruitService = new FruitServiceImpl();
    ```

    - 这行代码如果出现在 servlet 中的某个方法内部，那么这个 fruitService 的作用域（生命周期）应该就是这个方法级别。

    - 这行代码如果出现在 servlet 的类中，也就是说 fruitService 是一个成员变量，那么这个 fruitService 的作用域（生命周期）应该就是这个 servlet 实例级别。

  - 之后在 applicationContext.xml 中定义了这个 fruitService，然后通过解析 XML 产生 fruitService 实例，存放在 beanMap 中，这个 beanMap 在一个 BeanFactory 中。

    - 因此，转移（改变）了之前的 service 实例、dao 实例等的生命周期。控制权从程序员转移到 BeanFactory。这个现象我们称之为控制反转。

- 依赖注入

  - 之前在控制层出现代码中。

    ```java
    FruitService fruitService = new FruitServiceImpl();
    ```

    - 那么，controller 层和 service 层存在耦合。

  - 之后将代码进行修改。

    ```java
    FruitService fruitService = null;
    ```

    - 然后，在配置文件中配置。

      ```xml
      <beans>
          <bean id="fruitDao" class="com.myxh.fruit.dao.impl.FruitDaoImpl"/>

          <bean id="fruitService" class="com.myxh.fruit.service.impl.FruitServiceImpl">
              <!-- property 标签用来表示属性, name 表示属性名, ref 表示引用其他 bean 的 id 值 -->
              <property name="fruitDao" ref="fruitDao"/>
          </bean>

          <bean id="fruit" class="com.myxh.fruit.controllers.FruitController">
              <property name="fruitService" ref="fruitService"/>
          </bean>
      </beans>
      ```
