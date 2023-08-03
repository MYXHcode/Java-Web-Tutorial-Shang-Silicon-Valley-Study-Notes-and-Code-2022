package com.myxh.ssm.ioc;

import com.myxh.utils.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class ClassPathXmlApplicationContext implements BeanFactory
{
    private final Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext()
    {
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path)
    {
        if (StringUtils.isEmpty(path))
        {
            throw new RuntimeException("IOC 容器的配置文件没有指定！");
        }

        try
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

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

                    Class<?> BeanClass = Class.forName(className);

                    // 创建 bean 实例
                    Object beanObj = BeanClass.getDeclaredConstructor().newInstance();

                    // 将 bean 实例对象保存到 map 容器中
                    beanMap.put(beanId, beanObj);
                }
            }

            // 5. 组装 bean 之间的依赖关系
            for (int i = 0; i < beanNodeList.getLength(); i++)
            {
                Node beanNode = beanNodeList.item(i);

                if (beanNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element beanElement = (Element) beanNode;

                    String beanId = beanElement.getAttribute("id");

                    NodeList beanChildNodeList = beanElement.getChildNodes();

                    for (int j = 0; j < beanChildNodeList.getLength(); j++)
                    {
                        Node beanChildNode = beanChildNodeList.item(j);

                        if ((beanChildNode.getNodeType() == Node.ELEMENT_NODE) && "property".equals(beanChildNode.getNodeName()))
                        {
                            Element propertyElement = (Element) beanChildNode;

                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");

                            // 5.1 找到 propertyRef 对应的实例
                            Object refObj = beanMap.get(propertyRef);

                            // 5.2 将 refObj 设置到当前 bean 对应的实例的 property 属性上去
                            Object beanObj = beanMap.get(beanId);

                            Class<?> beanClass = beanObj.getClass();

                            Field propertyField = beanClass.getDeclaredField(propertyName);

                            propertyField.setAccessible(true);

                            propertyField.set(beanObj, refObj);
                        }
                    }
                }
            }
        }
        catch (ParserConfigurationException | IOException | SAXException | ClassNotFoundException |
               InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
               NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String id)
    {
        return beanMap.get(id);
    }
}
