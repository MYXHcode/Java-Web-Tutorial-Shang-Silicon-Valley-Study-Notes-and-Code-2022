package com.myxh.fruit.service.impl;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.fruit.service.FruitService;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/7
 */
public class FruitServiceImpl implements FruitService
{
    private final FruitDao fruitDao = null;

    /**
     * 获取所有的库存列表信息
     */
    @Override
    public List<Fruit> getFruitList()
    {
        return fruitDao.getFruitList();
    }

    /**
     * 获取指定页面上的库存列表信息, 每页显示 5 条
     */
    @Override
    public List<Fruit> getFruitListByPageNo(Integer pageNo)
    {
        return fruitDao.getFruitListByPageNo(pageNo);
    }

    /**
     * 根据关键词查询, 获取指定页面上的库存列表信息, 每页显示 5 条
     */
    @Override
    public List<Fruit> getFruitListByPageNoAndKeyWord(String keyword, Integer pageNo)
    {
        return fruitDao.getFruitListByPageNoAndKeyWord(keyword, pageNo);
    }

    /**
     * 根据 id 查看特定的水果库存记录
     */
    @Override
    public Fruit getFruitById(Integer id)
    {
        return fruitDao.getFruitById(id);
    }

    /**
     * 修改特定的库存记录
     */
    @Override
    public void updateFruit(Fruit fruit)
    {
        fruitDao.updateFruit(fruit);
    }

    /**
     * 根据 id 删除特定的库存记录
     */
    @Override
    public void deleteFruit(Integer id)
    {
        fruitDao.deleteFruit(id);
    }

    /**
     * 添加库存记录信息
     */
    @Override
    public void addFruit(Fruit fruit)
    {
        fruitDao.addFruit(fruit);
    }

    /**
     * 获取库存总页数
     */
    @Override
    public int getFruitPageCount()
    {
        int fruitCount = fruitDao.getFruitCount();
        int pageCount = (fruitCount + 5 - 1) / 5;

        return pageCount;
    }

    /**
     * 根据关键词获取库存总页数
     */
    @Override
    public Integer getFruitPageCountByKeyword(String keyword)
    {
        int fruitCount = fruitDao.getFruitCountByKeyword(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;

        return pageCount;
    }
}
