package com.myxh.book.pojo;

import java.math.BigDecimal;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public class Book
{
    private Integer id;
    private String bookName;
    private String author;
    private BigDecimal price;

    // 销量
    private Integer saleCount;

    // 库存
    private Integer bookCount;
    private String bookImage;

    // bookStatus 为 0 表示正常, bookStatus 为 -1 表示无效
    private Integer bookStatus = 0;

    public Book()
    {

    }

    public Book(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Integer getSaleCount()
    {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount)
    {
        this.saleCount = saleCount;
    }

    public Integer getBookCount()
    {
        return bookCount;
    }

    public void setBookCount(Integer bookCount)
    {
        this.bookCount = bookCount;
    }

    public String getBookImage()
    {
        return bookImage;
    }

    public void setBookImage(String bookImage)
    {
        this.bookImage = bookImage;
    }

    public Integer getBookStatus()
    {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus)
    {
        this.bookStatus = bookStatus;
    }
}
