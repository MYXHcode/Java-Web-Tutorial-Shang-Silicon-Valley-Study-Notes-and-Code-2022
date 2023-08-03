package com.myxh.book.service.impl;

import com.myxh.book.dao.BookDao;
import com.myxh.book.pojo.Book;
import com.myxh.book.service.BookService;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class BookServiceImpl implements BookService
{
    private BookDao bookDao;

    /**
     * 获取所有的图书列表信息
     */
    @Override
    public List<Book> getBookList()
    {
        return bookDao.getBookList();
    }

    /**
     * 根据图书 id 获取图书的详细信息
     */
    @Override
    public Book getBook(Integer id)
    {
        return bookDao.getBook(id);
    }
}
