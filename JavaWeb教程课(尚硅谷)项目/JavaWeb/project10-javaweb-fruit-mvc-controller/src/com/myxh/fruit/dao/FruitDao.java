package com.myxh.fruit.dao;

import com.myxh.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/4
 */
public interface FruitDao
{
    /**
     * 获取所有的库存列表信息
     */
    List<Fruit> getFruitList();

    /**
     * 获取指定页码上的库存列表信息, 每页显示 5 条
     */
    List<Fruit> getFruitListByPageNo(Integer pageNo);

    /**
     * 根据关键词查询, 获取指定页码上的库存列表信息, 每页显示 5 条
     */
    List<Fruit> getFruitListByPageNoAndKeyWord(String keyword, Integer pageNo);

    /**
     * 根据 id 获取特定的水果库存信息
     */
    Fruit getFruitById(Integer id);

    /**
     * 修改指定的库存记录
     */
    void updateFruit(Fruit fruit);

    /**
     * 根据 id 删除指定的库存记录
     */
    void deleteFruit(Integer id);

    /**
     * 添加新库存记录
     */
    void addFruit(Fruit fruit);

    /**
     * 查询库存总记录条数
     */
    int getFruitCount();

    /**
     * 根据关键词查询库存总记录条数
     */
    int getFruitCountByKeyword(String keyword);
}
