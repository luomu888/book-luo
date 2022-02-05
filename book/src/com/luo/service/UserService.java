package com.luo.service;

import com.luo.pojo.User;

public interface UserService {

    /**
     * ע���û�
     * @param user
     */
    public void registUser(User user);
    /**
     * ��¼
     * @param user
     * @return �������null��˵����¼ʧ�ܣ�������ֵ���ǵ�¼�ɹ�
     */
    public User login(User user);
    /**
     * ��� �û����Ƿ����
     * @param username
     * @return ����true��ʾ�û����Ѵ��ڣ�����false��ʾ�û�������
     */
    public boolean existsUsername(String username);

}
