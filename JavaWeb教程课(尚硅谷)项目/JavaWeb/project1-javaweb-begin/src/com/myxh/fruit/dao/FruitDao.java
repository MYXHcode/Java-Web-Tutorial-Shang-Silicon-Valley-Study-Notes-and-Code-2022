package com.myxh.fruit.dao;

import com.myxh.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/5/22
 */
public interface FruitDao
{
    /**
     * 查询库存列表
     */
    List<Fruit> getFruitList();

    /**
     * 新增库存
     */
    boolean addFruit(Fruit fruit);

    /**
     * 修改库存
     */
    boolean updateFruit(Fruit fruit);

    /**
     * 根据名称查询特定库存
     */
    Fruit getFruitByName(String name);

    /**
     * 删除特定库存记录
     */
    boolean deleteFruit(String name);
}
