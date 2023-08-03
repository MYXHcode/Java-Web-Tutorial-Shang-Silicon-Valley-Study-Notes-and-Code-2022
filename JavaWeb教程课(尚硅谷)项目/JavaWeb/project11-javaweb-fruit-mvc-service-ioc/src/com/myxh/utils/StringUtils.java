package com.myxh.utils;

/**
 * @author MYXH
 * @date 2023/7/7
 * @Description 字符串工具类, 判断字符串是否为 null 或者 ""
 */
public class StringUtils
{
    /**
     * 判断字符串是否 null 或者 ""
     */
    public static boolean isEmpty(String str)
    {
        return ((str == null) || ("".equals(str)));
    }

    /**
     * 判断字符串是否不是为 null 或者 ""
     */
    public static boolean isNotEmpty(String str)
    {
        return ((str != null) && (!"".equals(str)));
    }
}
