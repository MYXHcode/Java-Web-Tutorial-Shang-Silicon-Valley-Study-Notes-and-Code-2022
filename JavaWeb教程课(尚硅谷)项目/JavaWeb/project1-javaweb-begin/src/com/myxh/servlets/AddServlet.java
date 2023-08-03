package com.myxh.servlets;

import com.myxh.fruit.dao.impl.FruitDaoImpl;
import com.myxh.fruit.pojo.Fruit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * @author MYXH
 * @date 2023/5/22
 */
public class AddServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        /*
        get 方式在 Tomcat8 之后不需要设置编码

        string name = request.getParameter("name");

        1. 将字符串转换成字节数组
        byte[] bytes = name.getBytes("ISO-8859-1");

        2. 将字节数组按照设定的编码重新组装成字符串
        name = new string(bytes,"UTF-8");
         */

        /*
        post方式在 Tomcat10 之后不需要设置编码
        注意: 设置编码必须在所有的获取参数动作之前
         */
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        BigDecimal price = new BigDecimal(priceStr);
        String countStr = request.getParameter("count");
        Integer count = Integer.parseInt(countStr);
        String remark = request.getParameter("remark");

        FruitDaoImpl fruitDao = new FruitDaoImpl();
        boolean flag = fruitDao.addFruit(new Fruit(0, name, price, count, remark));

        System.out.println(flag ? "添加成功" : "添加失败");
    }
}
