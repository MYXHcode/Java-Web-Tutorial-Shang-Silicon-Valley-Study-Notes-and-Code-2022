package com.myxh.fruit.controllers;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.dao.impl.FruitDaoImpl;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/4
 */
public class FruitController
{
    private final FruitDao fruitDao = new FruitDaoImpl();

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        if (pageNo == null)
        {
            pageNo = 1;
        }

        if (StringUtils.isNotEmpty(oper) && "search".equals(oper))    // 如果 oper 不为空, 则是通过表单的查询按钮点击过来的
        {
            // 当是通过点击表单查询发送过来的请求时, pageNo 应该重置为 1
            pageNo = 1;

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
        // super.processTemplate("index", request, response);
        return "index";
    }

    private String add(String name, BigDecimal price, Integer count, String remark)
    {
        // 1. 执行添加
        Fruit fruit = new Fruit(0, name, price, count, remark);
        fruitDao.addFruit(fruit);

        // 2. 资源跳转
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String delete(Integer id)
    {
        if (id != null)
        {
            // 1. 执行删除
            fruitDao.deleteFruit(id);

            // 2. 资源跳转
            //response.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }

        return "error";
    }

    private String edit(Integer id, HttpServletRequest request)
    {
        if (id != null)
        {
            // 1. 执行编辑
            Fruit fruit = fruitDao.getFruitById(id);

            request.setAttribute("fruit", fruit);

            // 2. 资源跳转
            // super.processTemplate("edit", request, response);
            return "edit";
        }

        return "error";
    }

    private String update(Integer id, String name, BigDecimal price, Integer count, String remark)
    {
        // 1. 执行更新
        fruitDao.updateFruit(new Fruit(id, name, price, count, remark));

        // 2. 资源跳转
        // response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
}
