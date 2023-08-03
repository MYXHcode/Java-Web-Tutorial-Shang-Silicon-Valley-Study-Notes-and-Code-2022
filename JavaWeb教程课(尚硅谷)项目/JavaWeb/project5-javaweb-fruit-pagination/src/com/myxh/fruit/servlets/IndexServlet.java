package com.myxh.fruit.servlets;

import com.myxh.fruit.dao.impl.FruitDaoImpl;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.springmvc.ViewBaseServlet;
import com.myxh.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/6/25
 */
@WebServlet("/index")    // Servlet 从 3.0 版本开始支持注解方式的注册
public class IndexServlet extends ViewBaseServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // 设置当前页, 默认值为 1
        int pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");

        if (StringUtils.isNotEmpty(pageNoStr))
        {
            pageNo = Integer.parseInt(pageNoStr);
        }

        FruitDaoImpl fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitListByPageNo(pageNo);

        // 总记录条数
        int fruitCount = fruitDao.getFruitCount();

        // 总页数
        /*
        总记录条数      总页数
        1             1
        5             1
        6             2
        10            2
        11            3
        fruitCount    (fruitCount + 5 - 1) / 5
         */
        int pageCount = (fruitCount + 5 - 1) / 5;

        // 保存到 session 作用域
        HttpSession session = request.getSession();

        session.setAttribute("fruitList", fruitList);
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("pageCount", pageCount);

        /*
          此处的视图名称是 index
          那么 thymeleaf 会将这个逻辑视图名称对应到物理视圈名称上去
          逻辑视图名称∶       index
          物理视圈名称∶       view-prefix + 逻辑视图名称 + view-suffix
          所以真实的视图名称是: /             index       .html
         */
        super.processTemplate("index", request, response);
    }
}
