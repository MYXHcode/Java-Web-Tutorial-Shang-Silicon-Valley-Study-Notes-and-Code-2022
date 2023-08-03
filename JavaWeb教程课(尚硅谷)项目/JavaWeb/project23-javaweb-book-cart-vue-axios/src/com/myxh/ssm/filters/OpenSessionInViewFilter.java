package com.myxh.ssm.filters;

import com.myxh.ssm.transaction.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.sql.SQLException;

/**
 * @author MYXH
 * @date 2023/8/2
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    {
        try
        {
            TransactionManager.beginTransaction();
            System.out.println("开启事务...");

            filterChain.doFilter(servletRequest, servletResponse);

            TransactionManager.commit();
            System.out.println("提交事务...");
        }
        catch (Exception e)
        {
            e.printStackTrace();

            try
            {
                TransactionManager.rollback();
                System.out.println("回滚事务...");
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void destroy()
    {
        Filter.super.destroy();
    }
}
