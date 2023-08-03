package com.myxh.fruit.servlets;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.dao.impl.FruitDaoImpl;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.springmvc.ViewBaseServlet;
import com.myxh.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/6/29
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet
{
    private final FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        String operate = request.getParameter("operate");

        if (StringUtils.isEmpty(operate))
        {
            operate = "index";
        }

        switch (operate)
        {
            case "index" -> index(request, response);
            case "add" -> add(request, response);
            case "delete" -> delete(request, response);
            case "edit" -> edit(request, response);
            case "update" -> update(request, response);
            default -> throw new RemoteException("operate 值非法！");
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();

        // 设置当前页, 默认值为 1
        int pageNo = 1;
        String keyword;
        String oper = request.getParameter("oper");

        if (StringUtils.isNotEmpty(oper) && "search".equals(oper))    // 如果 oper 不为空, 则是通过表单的查询按钮点击过来的
        {
            // 当是通过点击表单查询发送过来的请求时, pageNo 应该重置为 1
            pageNo = 1;

            // keyword 应该从请求参数中获取
            keyword = request.getParameter("keyword");

            // 如果 keyword 为 null, 需要设置为空字符串"", 否则查询时会拼接成 %null%, 应该为 %%
            if (StringUtils.isEmpty(keyword))
            {
                keyword = "";
            }

            // 将 keyword 保存(覆盖)到 session 作用域
            session.setAttribute("keyword", keyword);
        }
        else    // 如果 oper 为空, 则不是通过表单的查询按钮点击过来的(比如点击页面的上一页、下一页或者直接在地址栏输入网址)
        {
            // 当不是通过点击表单查询发送过来的请求时, pageNo 应该从请求参数中获取
            String pageNoStr = request.getParameter("pageNo");

            if (StringUtils.isNotEmpty(pageNoStr))
            {
                // 如果从请求中读取到 pageNo, 则类型转换, 否则 pageNo 默认就是 1
                pageNo = Integer.parseInt(pageNoStr);
            }

            // 如果不是点击的查询按钮, 那么查询是基于 session 作用域中保存的现有 keyword 进行查询
            Object keywordObj = session.getAttribute("keyword");

            if (keywordObj != null)
            {
                keyword = (String) keywordObj;
            }
            else
            {
                keyword = "";
            }
        }

        // 保存到 session 作用域, 重新更新当前页的值
        session.setAttribute("pageNo", pageNo);

        FruitDaoImpl fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitListByPageNoAndKeyWord(keyword, pageNo);

        // 保存到 session 作用域
        session.setAttribute("fruitList", fruitList);

        // 总记录条数
        int fruitCount = fruitDao.getFruitCountByKeyword(keyword);

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

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
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
        // 此处需要重定向, 目的是重新给 FruitServlet 发请求, 重新获取 fruitList, 然后覆盖到 session 中, 使得 index.html 页面上显示的 session 中的数据是最新的
        response.sendRedirect("fruit.do");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String idStr = request.getParameter("id");

        if (StringUtils.isNotEmpty(idStr))
        {
            int id = Integer.parseInt(idStr);

            fruitDao.deleteFruit(id);

            // super.processTemplate("index", request, response);
            // request.getRequestDispatcher("index.html").forward(request, response);
            // 此处需要重定向, 目的是重新给 FruitServlet 发请求, 重新获取 fruitList, 然后覆盖到 session 中, 使得 index.html 页面上显示的 session 中的数据是最新的
            response.sendRedirect("fruit.do");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String idStr = request.getParameter("id");

        if (StringUtils.isNotEmpty(idStr))
        {
            int id = Integer.parseInt(idStr);
            Fruit fruit = fruitDao.getFruitById(id);

            request.setAttribute("fruit", fruit);
            super.processTemplate("edit", request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // 1. 设置编码
        request.setCharacterEncoding("utf-8");

        // 2. 获取参数
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        BigDecimal price = new BigDecimal(priceStr);
        String countStr = request.getParameter("count");
        Integer count = Integer.parseInt(countStr);
        String remark = request.getParameter("remark");

        // 3. 执行更新
        fruitDao.updateFruit(new Fruit(id, name, price, count, remark));

        // 4. 资源跳转
        // super.processTemplate("index", request, response);
        // request.getRequestDispatcher("index.html").forward(request, response);
        // 此处需要重定向, 目的是重新给 FruitServlet 发请求, 重新获取 fruitList, 然后覆盖到 session 中, 使得 index.html 页面上显示的 session 中的数据是最新的
        response.sendRedirect("fruit.do");
    }
}
