package com.myxh.fruit.dao.impl;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/6/24
 */
public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao
{
    /**
     * 获取所有的库存列表信息
     */
    @Override
    public List<Fruit> getFruitList()
    {
        return super.executeQuery("select * from t_fruit");
    }

    /**
     * 根据 id 获取特定的水果库存信息
     */
    @Override
    public Fruit getFruitById(Integer id)
    {
        return super.load("select * from t_fruit where id = ?", id);
    }

    /**
     * 修改指定的库存记录
     */
    @Override
    public void updateFruit(Fruit fruit)
    {
        String sql = "update t_fruit set name = ?, price = ?, count = ?, remark = ? where id = ?";
        super.executeUpdate(sql, fruit.getName(), fruit.getPrice(), fruit.getCount(), fruit.getRemark(), fruit.getId());
    }

    /**
     * 根据 id 删除指定的库存记录
     */
    @Override
    public void deleteFruit(Integer id)
    {
        super.executeUpdate("delete from t_fruit where id = ?", id);
    }

    /**
     * 添加新库存记录
     */
    @Override
    public void addFruit(Fruit fruit)
    {
        String sql = "insert into t_fruit values(0, ?, ?, ?, ?)";
        super.executeUpdate(sql, fruit.getName(), fruit.getPrice(), fruit.getCount(), fruit.getRemark());
    }
}
