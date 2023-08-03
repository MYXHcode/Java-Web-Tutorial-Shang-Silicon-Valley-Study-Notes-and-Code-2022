package com.myxh.fruit.service.impl;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.fruit.service.FruitService;
import com.myxh.ssm.basedao.ConnectionUtil;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/9
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
        System.out.println("Service 层 getFruitList 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        return fruitDao.getFruitList();
    }

    /**
     * 获取指定页面上的库存列表信息, 每页显示 5 条
     */
    @Override
    public List<Fruit> getFruitListByPageNo(Integer pageNo)
    {
        System.out.println("Service 层 getFruitListByPageNo 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        return fruitDao.getFruitListByPageNo(pageNo);
    }

    /**
     * 根据关键词查询, 获取指定页面上的库存列表信息, 每页显示 5 条
     */
    @Override
    public List<Fruit> getFruitListByPageNoAndKeyWord(String keyword, Integer pageNo)
    {
        System.out.println("Service 层 getFruitListByPageNoAndKeyWord 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        return fruitDao.getFruitListByPageNoAndKeyWord(keyword, pageNo);
    }

    /**
     * 根据 id 查看特定的水果库存记录
     */
    @Override
    public Fruit getFruitById(Integer id)
    {
        System.out.println("Service 层 getFruitById 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        return fruitDao.getFruitById(id);
    }

    /**
     * 修改特定的库存记录
     */
    @Override
    public void updateFruit(Fruit fruit)
    {
        System.out.println("Service 层 updateFruit 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        fruitDao.updateFruit(fruit);
    }

    /**
     * 根据 id 删除特定的库存记录
     */
    @Override
    public void deleteFruit(Integer id)
    {
        System.out.println("Service 层 deleteFruit 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        fruitDao.deleteFruit(id);
    }

    /**
     * 添加库存记录信息
     */
    @Override
    public void addFruit(Fruit fruit)
    {
        System.out.println("Service 层 addFruit 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        fruitDao.addFruit(fruit);

        /*
        验证事务管理机制, 保证线程安全
        当 updateFruit 报错时, 会回滚 addFruit 操作
         */
        /*
        Fruit fruitTemp = fruitDao.getFruitById(1);
        fruitTemp.setCount(-1);
        fruitDao.updateFruit(fruitTemp);
         */
    }

    /**
     * 获取库存总页数
     */
    @Override
    public int getFruitPageCount()
    {
        System.out.println("Service 层 getFruitPageCount 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

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
        System.out.println("Service 层 getFruitPageCountByKeyword 方法的 Connection 的哈希值:  " + ConnectionUtil.getConnection().toString());

        int fruitCount = fruitDao.getFruitCountByKeyword(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;

        return pageCount;
    }
}
