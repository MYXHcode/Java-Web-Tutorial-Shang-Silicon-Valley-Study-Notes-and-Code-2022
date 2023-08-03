package com.myxh.book.controller;

import com.myxh.book.pojo.Book;
import com.myxh.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class BookController
{
    private BookService bookService;

    public String index(HttpSession session)
    {
        List<Book> bookList = bookService.getBookList();

        session.setAttribute("bookList", bookList);

        return "index";
    }
}
