package com.myxh.ssm.basedao;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public abstract class BaseDao<T>
{
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;

    // T 的 Class对象
    private Class<T> entityClass;

    public BaseDao()
    {
        /*
        getClass() 获取 Class 对象, 当前我们执行的是 new userBasicDaoImpl(), 创建的是 userBasicDaoImpl 的实例,
        那么子类构造方法内部首先会调用父类（BaseDao）的无参构造方法,
        因此此处的 getClass() 会被执行，但是 getClass 获取的是 userBasicDaoImpl 的 Class,
        所以 getGenericSuperclass() 获取到的是 BaseDao 的 Class,
         */
        Type genericType = getClass().getGenericSuperclass();

        // ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();

        // 获取到的 <T> 中的 T 的真实的类型
        Type actualType = actualTypeArguments[0];

        try
        {
            entityClass = (Class<T>) Class.forName(actualType.getTypeName());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();

            throw new BaseDaoException("BaseDao.BaseDao() 报错！BaseDao 构造方法报错，可能的原因是没有指定 BaseDao<> 中的类型。");
        }
    }

    protected Connection getConnection()
    {
        return ConnectionUtil.getConnection();
    }

    protected void close(ResultSet resultSet, Connection connection)
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }

            // 是否回收连接, 需要考虑是不是事务
            if (connection != null && connection.getAutoCommit())
            {
                // 没有开启事务, 正常回收连接
                ConnectionUtil.closeConnection();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 给预处理命令对象设置参数
     */
    private void setParams(PreparedStatement statement, Object... params) throws SQLException
    {
        if (params != null && params.length > 0)
        {
            for (int i = 0; i < params.length; i++)
            {
                statement.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 执行更新, 返回影响行数
     */
    protected int executeUpdate(String sql, Object... params)
    {
        boolean insertFlag;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");

        try
        {
            connection = getConnection();
            if (insertFlag)
            {
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            else
            {
                statement = connection.prepareStatement(sql);
            }

            setParams(statement, params);
            int count = statement.executeUpdate();

            if (insertFlag)
            {
                resultSet = statement.getGeneratedKeys();

                if (resultSet.next())
                {
                    return ((Long) resultSet.getLong(1)).intValue();
                }
            }

            return count;
        }
        catch (SQLException e)
        {
            e.printStackTrace();

            throw new BaseDaoException("BaseDao.executeUpdate() 报错！");
        }
        finally
        {
            close(resultSet, connection);
        }
    }

    /**
     * 通过反射技术给 obj 对象的 property 属性赋 propertyValue 值
     */
    private void setValue(Object obj, String property, Object propertyValue) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException
    {
        Class<?> clazz = obj.getClass();

        // 获取 property 这个字符串对应的属性名, 比如 "id" 去找 obj 对象中的 id 属性
        Field field = clazz.getDeclaredField(property);

        // 获取当前字段的类型名称
        String typeName = field.getType().getName();

        // 判断如果是自定义类型, 则需要调用这个自定义类的带一个参数的构造方法, 创建出这个自定义的实例对象, 然后将实例对象赋值给这个属性
        if (isMyType(typeName))
        {
            // 假设 typeName 是 com.myxh.qqzone.pojo.UserBasic
            Class<?> typeNameClass = Class.forName(typeName);
            Constructor<?> constructor = typeNameClass.getDeclaredConstructor(Integer.class);
            propertyValue = constructor.newInstance(propertyValue);
        }

        field.setAccessible(true);
        field.set(obj, propertyValue);
    }

    private static boolean isNotMyType(String typeName)
    {
        return
                // 基本数据类型
                "java.lang.Byte".equals(typeName)
                        || "java.lang.Short".equals(typeName)
                        || "java.lang.Integer".equals(typeName)
                        || "java.lang.Long".equals(typeName)
                        || "java.lang.Float".equals(typeName)
                        || "java.lang.Double".equals(typeName)
                        || "java.lang.Character".equals(typeName)
                        || "java.lang.Boolean".equals(typeName)

                        // 字符串类型
                        || "java.lang.String".equals(typeName)

                        // 数学类型
                        || "java.math.BigDecimal".equals(typeName)

                        // 日期时间类型
                        || "java.util.Date".equals(typeName)
                        || "java.time.LocalDateTime".equals(typeName)
                        || "java.sql.Timestamp".equals(typeName)

                        // 集合类型
                        || "java.util.List".equals(typeName)
                        || "java.util.Map".equals(typeName)
                        || "java.util.Set".equals(typeName)
                        || "java.util.ArrayList".equals(typeName)
                        || "java.util.HashMap".equals(typeName)

                        // 文件类型
                        || "java.io.File".equals(typeName)

                        //网络类型
                        || "java.net.URL".equals(typeName)

                        //异常类型
                        || "java.lang.Exception".equals(typeName)

                        // I/O 类型
                        || "java.io.InputStream".equals(typeName)
                        || "java.io.OutputStream".equals(typeName)

                        // 反射类型
                        || "java.lang.reflect.Method".equals(typeName)

                        // 并发类型
                        || "java.util.concurrent.Future".equals(typeName)
                        || "java.util.concurrent.Callable".equals(typeName)

                        // 泛型类型
                        || "java.util.List<String>".equals(typeName)

                        // 注解类型
                        || "java.lang.annotation.Annotation".equals(typeName)

                        // JDBC类型
                        || "java.sql.Connection".equals(typeName)
                        || "java.sql.Statement".equals(typeName)

                        // XML处理类型
                        || "javax.xml.parsers.DocumentBuilder".equals(typeName)
                        || "org.w3c.dom.Document".equals(typeName)

                        // 正则表达式类型
                        || "java.util.regex.Pattern".equals(typeName)

                        // 网络传输类型
                        || "javax.servlet.http.HttpServletRequest".equals(typeName);
    }

    private static boolean isMyType(String typeName)
    {
        return !isNotMyType(typeName);
    }

    /**
     * 执行复杂查询，返回例如统计结果
     */
    protected Object[] executeComplexQuery(String sql, Object... params)
    {
        try
        {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            resultSet = statement.executeQuery();

            /*
            通过 resultSet 可以获取结果集的元数据
            元数据: 描述结果集数据的数据, 就是这个结果集有哪些列, 什么类型
             */
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 获取结果集的列数
            int columnCount = metaData.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];

            // 解析 resultSet
            if (resultSet.next())
            {
                for (int i = 0; i < columnCount; i++)
                {
                    Object columnValue = resultSet.getObject(i + 1);
                    columnValueArr[i] = columnValue;
                }

                return columnValueArr;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();

            throw new BaseDaoException("BaseDao.executeComplexQuery() 报错！");
        }
        finally
        {
            close(resultSet, connection);
        }

        return null;
    }

    /**
     * 执行查询, 返回单个实体对象
     */
    protected T load(String sql, Object... params)
    {
        try
        {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            resultSet = statement.executeQuery();

            /*
            通过 resultSet 可以获取结果集的元数据
            元数据: 描述结果集数据的数据, 就是这个结果集有哪些列, 什么类型
             */
            ResultSetMetaData metaData = resultSet.getMetaData();

            //获取结果集的列数
            int columnCount = metaData.getColumnCount();

            // 解析 resultSet
            if (resultSet.next())
            {
                T entity = entityClass.getDeclaredConstructor().newInstance();

                for (int i = 0; i < columnCount; i++)
                {
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    setValue(entity, columnName, columnValue);
                }

                return entity;
            }
        }
        catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException |
               InvocationTargetException | NoSuchFieldException | ClassNotFoundException e)
        {
            e.printStackTrace();

            throw new BaseDaoException("BaseDao.load() 报错！");
        }
        finally
        {
            close(resultSet, connection);
        }

        return null;
    }


    /**
     * 执行查询, 返回List
     */
    protected List<T> executeQuery(String sql, Object... params)
    {
        List<T> list = new ArrayList<>();

        try
        {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            resultSet = statement.executeQuery();

            /*
            通过 resultSet 可以获取结果集的元数据
            元数据: 描述结果集数据的数据, 就是这个结果集有哪些列, 什么类型
             */
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 获取结果集的列数
            int columnCount = metaData.getColumnCount();

            // 解析 resultSet
            while (resultSet.next())
            {
                T entity = entityClass.getDeclaredConstructor().newInstance();

                for (int i = 0; i < columnCount; i++)
                {
                    // String columnName = metaData.getColumnName(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Object columnValue = resultSet.getObject(i + 1);

                    // setValue(entity, columnName, columnValue);
                    setValue(entity, columnLabel, columnValue);

                }

                list.add(entity);
            }
        }
        catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException |
               InvocationTargetException | NoSuchFieldException | ClassNotFoundException e)
        {
            e.printStackTrace();

            throw new BaseDaoException("BaseDao.executeQuery() 报错！");
        }
        finally
        {
            close(resultSet, connection);
        }

        return list;
    }
}
