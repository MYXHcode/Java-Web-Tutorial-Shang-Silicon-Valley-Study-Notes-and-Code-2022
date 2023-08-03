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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        // 设置当前页, 默认值为 1
        int pageNo = 1;
        String keyword;
        String operate = request.getParameter("operate");

        if (StringUtils.isNotEmpty(operate) && "search".equals(operate))    // 如果 operate 不为空, 则是通过表单的查询按钮点击过来的
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
        else    // 如果 operate 为空, 则不是通过表单的查询按钮点击过来的(比如点击页面的上一页、下一页或者直接在地址栏输入网址)
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
}
