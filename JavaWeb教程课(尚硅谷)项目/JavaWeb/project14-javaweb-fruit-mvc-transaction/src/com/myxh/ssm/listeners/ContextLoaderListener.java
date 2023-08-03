package com.myxh.ssm.listeners;

import com.myxh.ssm.ioc.BeanFactory;
import com.myxh.ssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author MYXH
 * @date 2023/7/9
 * @Description 监听上下文启动, 在上下文启动的时候去创建 IOC 容器, 然后将其保存到 application 作用城,
 * 之后中央控制器再从 application 作用城中去获取 IOC 容器
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        // 1. 获取 ServletContext 对象
        ServletContext application = servletContextEvent.getServletContext();

        // 2. 获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");

        // 3. 创建 IOC 容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);

        // 4. 将 IOC 容器保存到 application 作用域
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        ServletContextListener.super.contextDestroyed(servletContextEvent);
    }
}
