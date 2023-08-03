package com.myxh.fruit.dao.impl;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.dao.base.BaseDao;
import com.myxh.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/5/22
 */
public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao
{
    /**
     * 查询库存列表
     */
    @Override
    public List<Fruit> getFruitList()
    {
        return super.executeQuery("select * from t_fruit");
    }

    /**
     * 新增库存
     */
    @Override
    public boolean addFruit(Fruit fruit)
    {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = super.executeUpdate(sql,fruit.getName(),fruit.getPrice(),fruit.getCount(),fruit.getRemark()) ;

        // insert 语句返回的是自增列的值，而不是影响行数
        return count > 0;
    }

    /**
     * 修改库存
     */
    @Override
    public boolean updateFruit(Fruit fruit)
    {
        String sql = "update t_fruit set count = ? where id = ? " ;
        return super.executeUpdate(sql,fruit.getCount(),fruit.getId()) > 0;
    }

    /**
     * 根据名称查询特定库存
     */
    @Override
    public Fruit getFruitByName(String name)
    {
        return super.load("select * from t_fruit where name like ? ", name);
    }

    /**
     * 删除特定库存记录
     */
    @Override
    public boolean deleteFruit(String name)
    {
        String sql = "delete from t_fruit where name like ? " ;
        return super.executeUpdate(sql,name) > 0;
    }
}