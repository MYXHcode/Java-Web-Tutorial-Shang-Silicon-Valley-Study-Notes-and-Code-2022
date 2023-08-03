package com.myxh.fruit.servlets;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.dao.impl.FruitDaoImpl;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.springmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author MYXH
 * @date 2023/6/24
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet
{
    private final FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // 1. 设置编码
        request.setCharacterEncoding("utf-8");

        // 2. 获取参数
        String name = request.getParameter("name");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        String remark = request.getParameter("remark");

        // 3. 执行添加
        Fruit fruit = new Fruit(0, name, price, count, remark);
        fruitDao.addFruit(fruit);

        // 4. 资源跳转
        // super.processTemplate("index", request, response);
        // request.getRequestDispatcher("index.html").forward(request, response);
        // 此处需要重定向, 目的是重新给 IndexServlet 发请求, 重新获取 fruitList, 然后覆盖到 session 中, 使得 index.html 页面上显示的 session 中的数据是最新的
        response.sendRedirect("index");
    }
}
