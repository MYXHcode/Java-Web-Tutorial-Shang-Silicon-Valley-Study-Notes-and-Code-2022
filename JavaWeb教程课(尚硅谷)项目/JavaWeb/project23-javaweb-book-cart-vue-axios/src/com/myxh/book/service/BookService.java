package com.myxh.book.service;

import com.myxh.book.pojo.Book;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public interface BookService
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
