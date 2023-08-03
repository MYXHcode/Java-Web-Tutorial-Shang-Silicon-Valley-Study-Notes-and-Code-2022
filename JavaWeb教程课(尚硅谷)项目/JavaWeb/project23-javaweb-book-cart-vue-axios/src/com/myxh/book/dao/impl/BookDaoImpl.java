package com.myxh.book.dao.impl;

import com.myxh.book.dao.BookDao;
import com.myxh.ssm.basedao.BaseDao;

import com.myxh.book.pojo.Book;

import java.util.List;


/**
 * @author MYXH
 * @date 2023/8/2
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao
{
    /**
     * 获取所有的图书列表信息
     */
    @Override
    public List<Book> getBookList()
    {
        String sql= "select * from t_book where bookStatus = 0";

        return executeQuery(sql);
    }

    /**
     * 根据图书 id 获取图书的详细信息
     */
    @Override
    public Book getBook(Integer id)
    {
        String sql="select * from t_book where id = ?";

        return load(sql, id);
    }
}
