package com.luo.dao;

import com.luo.pojo.User;

public interface UserDao {

    /**
     * �����û�����ѯ�û���Ϣ
     * @param username �û���
     * @return �������null,˵��û������û�����֮��Ȼ
     */
    public User queryUserByUsername(String username);
    /**
     * ���� �û����������ѯ�û���Ϣ
     * @param username
     * @param password
     * @return �������null,˵���û������������,��֮��Ȼ
     */
    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     * �����û���Ϣ
     * @param user
     * @return ����-1��ʾ����ʧ�ܣ�������sql���Ӱ�������
     */
    public int saveUser(User user);
}
