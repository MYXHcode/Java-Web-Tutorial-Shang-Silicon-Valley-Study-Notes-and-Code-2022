package com.myxh.fruit.dao.impl;

import com.myxh.fruit.dao.FruitDao;
import com.myxh.fruit.pojo.Fruit;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/6/11
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
}
