package com.luo.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login(){
        System.out.println("login����");
    }
    public void regist(){
        System.out.println("regist����");
    }
    public void updateUser(){
        System.out.println("updateUser����");
    }
    public void updateUserPassword(){
        System.out.println("updateUserPassword����");
    }

    public static void main(String[] args) {
        String action="updateUserPassword";
        {
            try {
                // ��ȡactionҵ������ַ�������ȡ��Ӧ��ҵ�� �����������
                Method  method= UserServletTest.class.getDeclaredMethod(action);
                System.out.println(method);
                // ����Ŀ��ҵ�� ����
                method.invoke(new UserServletTest());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
