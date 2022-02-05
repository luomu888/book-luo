package com.luo.test;

import com.luo.dao.UserDao;
import com.luo.dao.impl.UserDaoImpl;
import com.luo.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin1234") == null ){
            System.out.println("�û������ã�");
        } else {
            System.out.println("�û����Ѵ��ڣ�");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if ( userDao.queryUserByUsernameAndPassword("admin","admin1234") == null) {
            System.out.println("�û�����������󣬵�¼ʧ��");
        } else {
            System.out.println("��ѯ�ɹ�");
        }
    }

    @Test
    public void saveUser() {
        System.out.println( userDao.saveUser(new User(1,"luo", "123456", "luo@qq.com")) );
    }
}