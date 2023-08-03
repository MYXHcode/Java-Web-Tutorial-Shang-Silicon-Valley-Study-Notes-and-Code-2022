package com.myxh.fruit.dao;

import com.myxh.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/6/11
 */
public interface FruitDao
{
    /**
     * 获取所有的库存列表信息
     */
    List<Fruit> getFruitList();
}
