package com.luo.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login(){
        System.out.println("login方法");
    }
    public void regist(){
        System.out.println("regist方法");
    }
    public void updateUser(){
        System.out.println("updateUser方法");
    }
    public void updateUserPassword(){
        System.out.println("updateUserPassword方法");
    }

    public static void main(String[] args) {
        String action="updateUserPassword";
        {
            try {
                // 获取action业务鉴别字符串，获取相应的业务 方法反射对象
                Method  method= UserServletTest.class.getDeclaredMethod(action);
                System.out.println(method);
                // 调用目标业务 方法
                method.invoke(new UserServletTest());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
