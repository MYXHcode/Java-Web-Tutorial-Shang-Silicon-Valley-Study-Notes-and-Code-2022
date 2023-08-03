package com.myxh.book.dao;

import com.myxh.book.pojo.Book;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/28
 */
public interface BookDao
{
    /**
     * 获取所有的图书列表信息
     */
    List<Book> getBookList();

    /**
     * 根据图书 id 获取图书的详细信息
     */
    Book getBook(Integer id);
}
