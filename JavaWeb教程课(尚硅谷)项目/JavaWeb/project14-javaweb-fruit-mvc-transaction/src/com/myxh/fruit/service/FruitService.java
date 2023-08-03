package com.myxh.fruit.service;

import com.myxh.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/9
 */
public interface FruitService
{
    /**
     * 获取所有的库存列表信息
     */
    List<Fruit> getFruitList();

    /**
     * 获取指定页面上的库存列表信息, 每页显示 5 条
     */
    List<Fruit> getFruitListByPageNo(Integer pageNo);

    /**
     * 根据关键词查询, 获取指定页面上的库存列表信息, 每页显示 5 条
     */
    List<Fruit> getFruitListByPageNoAndKeyWord(String keyword, Integer pageNo);

    /**
     * 根据 id 查看特定的水果库存记录
     */
    Fruit getFruitById(Integer id);

    /**
     * 修改特定的库存记录
     */
    void updateFruit(Fruit fruit);

    /**
     * 根据 id 删除特定的库存记录
     */
    void deleteFruit(Integer id);

    /**
     * 添加库存记录信息
     */
    void addFruit(Fruit fruit);

    /**
     * 获取库存总页数
     */
    int getFruitPageCount();

    /**
     * 根据关键词获取库存总页数
     */
    Integer getFruitPageCountByKeyword(String keyword);
}
