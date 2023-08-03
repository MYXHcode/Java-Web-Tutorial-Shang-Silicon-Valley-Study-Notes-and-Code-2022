package com.myxh.book.pojo;

/**
 * @author MYXH
 * @date 2023/7/23
 */
public class OrderItem
{
    private Integer id;

    // OrderItem : Book -> M : 1
    private Book book;
    private Integer buyCount;

    // OrderItem : Order -> M : 1
    private Order order;

    public OrderItem()
    {

    }

    public OrderItem(Integer id)
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

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public Integer getBuyCount()
    {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount)
    {
        this.buyCount = buyCount;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
}
