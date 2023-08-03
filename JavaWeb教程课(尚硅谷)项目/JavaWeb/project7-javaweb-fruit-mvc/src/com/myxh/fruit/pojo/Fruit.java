package com.myxh.fruit.pojo;

import java.math.BigDecimal;

/**
 * @author MYXH
 * @date 2023/6/29
 */
public class Fruit
{
    private Integer id;
    private String name;
    private BigDecimal price ;
    private Integer count;
    private String remark ;

    public Fruit()
    {

    }

    public Fruit(Integer id, String name, BigDecimal price, Integer count, String remark)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.remark = remark;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public String toString()
    {
        return id + "\t\t" + name + "\t\t" + price +"\t\t" + count +"\t\t" + remark ;
    }
}
