package com.myxh.ssm.springmvc;

import com.myxh.utils.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MYXH
 * @date 2023/7/4
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet
{
    private final Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void init()
    {
        super.init();

        try
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");

            // 1. 创建 DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            // 2. 创建 DocumentBuilder 对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // 3. 创建 Document 对象
            Document document = documentBuilder.parse(inputStream);

            // 4. 获取所有的 bean 节点
            NodeList beanNodeList = document.getElementsByTagName("bean");

            for (int i = 0; i < beanNodeList.getLength(); i++)
            {
                Node beanNode = beanNodeList.item(i);

                if (beanNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element beanElement = (Element) beanNode;

                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");

                    Class<?> controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.getDeclaredConstructor().newInstance();

                    beanMap.put(beanId, beanObj);
                }
            }
        }
        catch (ParserConfigurationException | IOException | SAXException | ClassNotFoundException |
               InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        /*
        URL 是: http://localhost:8080/project10-javaweb-fruit-mvc-controller/fruit.do
        那么 servletPath 是: /fruit.do
        第 1 步: /fruit.do -> fruit
        第 2 步: fruit -> FruitController
         */
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDoIndex);

        Object controllerBeanObj = beanMap.get(servletPath);

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
    }
}
