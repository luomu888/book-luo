package com.luo.dao;

import com.luo.pojo.Order;

import java.util.List;


public interface OrderDao {
    /**
     * ���涩��
     * @param order ��������
     * @return ��Ӱ�������
     */
    int saveOrder(Order order);

    /**
     * ��ѯȫ������
     * @return �����б�
     */
    List<Order> queryOrder();

    /**
     * �޸Ķ���״̬
     * @param orderId ������
     * @param status ����״̬
     * @return ��Ӱ�������
     */
    int changeOrderStatus(String orderId, Integer status);

    /**
     * �����û���Ų�ѯ������Ϣ
     * @param userId �û����
     * @return �����б�
     */
    List<Order> queryOrderByUserId(Integer userId);
}
