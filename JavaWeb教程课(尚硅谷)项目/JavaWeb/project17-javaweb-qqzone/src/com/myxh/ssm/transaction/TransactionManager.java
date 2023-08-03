package com.myxh.ssm.transaction;

import com.myxh.ssm.basedao.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class TransactionManager
{

    /**
     * 开启事务
     */
    public static void beginTransaction() throws SQLException
    {
        ConnectionUtil.getConnection().setAutoCommit(false);
    }

    /**
     * 提交事务
     */
    public static void commit() throws SQLException
    {
        Connection connection = ConnectionUtil.getConnection();

        connection.commit();
        ConnectionUtil.closeConnection();

    }

    /**
     * 回滚事务
     */
    public static void rollback() throws SQLException
    {
        Connection connection = ConnectionUtil.getConnection();

        ConnectionUtil.getConnection().rollback();
        ConnectionUtil.closeConnection();
    }
}
