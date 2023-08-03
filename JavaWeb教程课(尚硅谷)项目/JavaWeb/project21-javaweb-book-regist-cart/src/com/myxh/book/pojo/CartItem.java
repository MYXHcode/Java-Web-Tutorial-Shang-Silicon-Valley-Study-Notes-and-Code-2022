package com.myxh.book.pojo;

/**
 * @author MYXH
 * @date 2023/7/30
 */
// 应该还需要编写一个 Cart 类, 代表购物车这个实体
public class CartItem
{
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User user;

    public CartItem()
    {

    }

    public CartItem(Integer id, Integer buyCount)
    {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User user)
    {
        this.book = book;
        this.buyCount = buyCount;
        this.user = user;
    }

    public CartItem(Integer id)
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
