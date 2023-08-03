package com.myxh.fruit.dao.impl;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/2
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
     * 获取指定页码上的库存列表信息, 每页显示 5 条
     */
    @Override
    public List<Fruit> getFruitListByPageNo(Integer pageNo)
    {
        return super.executeQuery("select * from t_fruit limit ? , 5", (pageNo - 1) * 5);
    }

    /**
     * 根据关键词查询, 获取指定页码上的库存列表信息, 每页显示 5 条
     */
    @Override
    public List<Fruit> getFruitListByPageNoAndKeyWord(String keyword, Integer pageNo)
    {
        String sql = "select * from t_fruit where name like ? or remark like ? limit ?, 5";

        return super.executeQuery(sql, "%" + keyword + "%", "%" + keyword + "%", (pageNo - 1) * 5);
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

    /**
     * 查询库存总记录条数
     */
    @Override
    public int getFruitCount()
    {
        // 报错: java.lang.ClassCastException: class java.lang.Long cannot be cast to class java.lang.Integer (java.lang.Long and java.lang.Integer are in module java.base of loader 'bootstrap')
        // return (Integer) super.executeComplexQuery("select count(*) from t_fruit")[0];
        return ((Long) super.executeComplexQuery("select count(*) from t_fruit")[0]).intValue();
    }

    /**
     * 根据关键词查询库存总记录条数
     */
    @Override
    public int getFruitCountByKeyword(String keyword)
    {
        String sql = "select count(*) from t_fruit where name like ? or remark like ?";

        return ((Long) super.executeComplexQuery(sql, "%" + keyword + "%", "%" + keyword + "%")[0]).intValue();
    }
}
