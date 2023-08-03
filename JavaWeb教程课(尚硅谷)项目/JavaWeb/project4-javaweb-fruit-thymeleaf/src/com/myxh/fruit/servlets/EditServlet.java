package com.myxh.fruit.servlets;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.dao.impl.FruitDaoImpl;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.springmvc.ViewBaseServlet;
import com.myxh.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/6/24
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet
{
    private final FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
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
}
