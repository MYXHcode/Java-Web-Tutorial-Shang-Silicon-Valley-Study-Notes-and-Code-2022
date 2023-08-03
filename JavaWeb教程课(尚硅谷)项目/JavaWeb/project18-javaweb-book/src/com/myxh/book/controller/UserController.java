package com.myxh.book.controller;

import com.myxh.book.pojo.User;
import com.myxh.book.service.UserService;

/**
 * @author MYXH
 * @date 2023/7/23
 */
public class UserController
{
    private UserService userService;

    public String login(String name, String password)
    {
        User user = userService.login(name, password);

        System.out.println("user = " + user.toString());

        return "index";
    }
}
