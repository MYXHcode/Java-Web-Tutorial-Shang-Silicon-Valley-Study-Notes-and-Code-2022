package com.myxh.ssm.springmvc;

import com.myxh.ssm.ioc.BeanFactory;
import com.myxh.utils.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.nio.file.DirectoryNotEmptyException;
import java.rmi.RemoteException;

/**
 * @author MYXH
 * @date 2023/7/9
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet
{
    private BeanFactory beanFactory;

    @Override
    public void init()
    {
        super.init();

        /*
        之前是在此处主动创建 IOC 容器的,
        现在优化为从 application 作用域中去获取 IOC 容器
         */
        // beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");

        if (beanFactoryObj != null)
        {
            beanFactory = (BeanFactory) beanFactoryObj;
        }
        else
        {
            throw new RuntimeException("IOC 容器获取失败！");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // 设置编码
        // request.setCharacterEncoding("UTF-8");

        /*
        URL 是: http://localhost:8080/project14_javaweb_fruit_mvc_transaction/fruit.do
        那么 servletPath 是: /fruit.do
        第 1 步: /fruit.do -> fruit
        第 2 步: fruit -> FruitController
         */
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDoIndex);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");

        if (StringUtils.isEmpty(operate))
        {
            operate = "index";
        }

        try
        {
            // 获取当前类中所有的方法名称
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();

            for (Method method : methods)
            {
                if (operate.equals(method.getName()))
                {
                    // 1. 统一获取请求参数
                    // 1.1 获取当蔚方法的参数, 返回参数数组
                    Parameter[] parameters = method.getParameters();

                    // 1.2 parameterValues 用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];

                    for (int i = 0; i < parameters.length; i++)
                    {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();

                        // 如果参数名是 request, response, session, 那么就不是通过请求中获取参数的方式
                        if ("request".equals(parameterName))
                        {
                            parameterValues[i] = request;
                        }
                        else if ("response".equals(parameterName))
                        {
                            parameterValues[i] = response;
                        }
                        else if ("session".equals(parameterName))
                        {
                            parameterValues[i] = request.getSession();
                        }
                        else
                        {
                            // 从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);

                            // 常见错误: java.lang.IllegalArgumentException: argument type mismatch
                            String typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue;

                            if (parameterObj != null)
                            {
                                if ("java.lang.Integer".equals(typeName))
                                {
                                    parameterObj = Integer.parseInt(parameterValue);    // "2", 应该为 2
                                }
                                else if ("java.math.BigDecimal".equals(typeName))
                                {
                                    parameterObj = new BigDecimal(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj;
                        }
                    }

                    // 2. controller 组件中的方法调用
                    // 找到和 operate 同名的方法, 通过反射技术调用它
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    // 3. 视图处理
                    String methodReturnStr = (String) returnObj;

                    if (methodReturnStr.startsWith("redirect:"))    // 比如 "redirect:fruit.do"
                    {
                        String redirectStr = methodReturnStr.substring("redirect:".length());

                        response.sendRedirect(redirectStr);
                    }
                    else
                    {
                        super.processTemplate(methodReturnStr, request, response);    // 比如 "edit"
                    }
                }
            }
        }
        catch (InvocationTargetException | IllegalAccessException e)
        {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();

            throw new RemoteException("operate 值非法！");
        }
        catch (Exception e)
        {
            e.printStackTrace();

            throw new DirectoryNotEmptyException("DispatcherServlet.service() 报错！");
        }
    }
}
