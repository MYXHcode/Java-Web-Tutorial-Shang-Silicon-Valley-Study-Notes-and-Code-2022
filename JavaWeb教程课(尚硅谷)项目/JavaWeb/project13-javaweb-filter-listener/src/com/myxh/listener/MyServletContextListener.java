package com.myxh.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author MYXH
 * @date 2023/7/8
 */
@WebListener
public class MyServletContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        System.out.println("Servlet 上下文对象初始化动作被监听...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        System.out.println("Servlet 上下文对象销毁动作被监听...");
    }
}
