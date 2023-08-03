package com.myxh.ssm.basedao;

import com.myxh.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class ConnectionUtil
{
    protected static Connection connection;

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static Connection createConnection()
    {
        try
        {
            connection = JdbcUtils.getConnection();

            return connection;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static Connection getConnection()
    {
        Connection connection = threadLocal.get();

        if (connection == null)
        {
            connection = createConnection();
            threadLocal.set(connection);
        }

        return threadLocal.get();
    }

    public static void closeConnection() throws SQLException
    {
        Connection connection = threadLocal.get();

        if (connection == null)
        {
           return;
        }

        if (!connection.isClosed())
        {
            JdbcUtils.freeConnection();

            // threadLocal.set(null);
            threadLocal.remove();
        }
    }
}
