package com.luo.test;

import com.luo.pojo.User;
import com.luo.service.UserService;
import com.luo.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
        userService.registUser(new User(null, "abc168", "666666", "abc168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "abc168", "666666", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("wzg16888")) {
            System.out.println("�û����Ѵ��ڣ�");
        } else {
            System.out.println("�û������ã�");
        }
    }
}