package com.luo.service.impl;

import com.luo.dao.UserDao;
import com.luo.dao.impl.UserDaoImpl;
import com.luo.pojo.User;
import com.luo.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            // ����null,˵��û�鵽��û�鵽��ʾ����
            return false;
        }
        return true;

    }
}
