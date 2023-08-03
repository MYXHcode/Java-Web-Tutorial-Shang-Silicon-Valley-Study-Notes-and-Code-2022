package com.myxh.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class Cart
{
    // 购物车中购物车项的集合, 这个 Map 集合中的 Key 是 Book 的 id
    private Map<Integer, CartItem> cartItemMap;

    // 购物车的总金额
    private BigDecimal totalMoney;

    // 购物车中购物项的数量
    private Integer totalCount;

    // 购物车中书本的总数量, 而不是购物车项的总数量
    private Integer totalBookCount;

    public Cart()
    {

    }

    public Map<Integer, CartItem> getCartItemMap()
    {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap)
    {
        this.cartItemMap = cartItemMap;
    }

    public BigDecimal getTotalMoney()
    {
        totalMoney = BigDecimal.valueOf(0.0);

        if ((cartItemMap != null) && (cartItemMap.size() > 0))
        {
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();

            for (Map.Entry<Integer, CartItem> cartItemEntry : entries)
            {
                CartItem cartItem = cartItemEntry.getValue();

                BigDecimal buyCount = new BigDecimal(cartItem.getBuyCount());

                totalMoney = totalMoney.add(cartItem.getBook().getPrice().multiply(buyCount));
            }
        }

        return totalMoney;
    }

    public Integer getTotalCount()
    {
        totalCount = 0;

        if ((cartItemMap != null) && cartItemMap.size() > 0)
        {
            totalCount = cartItemMap.size();
        }

        return totalCount;
    }

    public Integer getTotalBookCount()
    {
        totalBookCount = 0;

        if ((cartItemMap != null) && cartItemMap.size() > 0)
        {
            for (CartItem cartItem : cartItemMap.values())
            {
                totalBookCount = totalBookCount + cartItem.getBuyCount();
            }
        }

        return totalBookCount;
    }
}
