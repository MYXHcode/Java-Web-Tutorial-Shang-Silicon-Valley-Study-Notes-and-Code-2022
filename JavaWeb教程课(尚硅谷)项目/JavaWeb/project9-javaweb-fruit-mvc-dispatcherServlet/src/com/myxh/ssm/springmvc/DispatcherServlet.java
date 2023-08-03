package com.myxh.ssm.springmvc;

import com.myxh.utils.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MYXH
 * @date 2023/7/3
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet
{
    private final Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void init()
    {
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
                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
                    setServletContextMethod.invoke(beanObj, this.getServletContext());

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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, RemoteException
    {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        /*
        URL 是: http://localhost:8080/project9_javaweb_fruit_mvc_dispatcherServlet/fruit.do
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
            // 获取当前类中对应的方法名称
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);

            if (method != null)
            {
                // 找到和 operate 同名的方法, 通过反射技术调用它
                method.setAccessible(true);
                method.invoke(controllerBeanObj, request, response);
            }
            else
            {
                throw new RemoteException("operate 值非法！");
            }
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
        {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }
}

