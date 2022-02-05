package com.luo.dao;

import com.luo.pojo.OrderItem;

import java.util.List;


public interface OrderItemDao {
    /**
     * ���涩����
     * @param orderItem ���������
     * @return ��Ӱ�������
     */
    int saveOrderItem(OrderItem orderItem);

    /***
     * ���ݶ����Ų�ѯ����
     * @param orderId ������
     * @return �����б�
     */
    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
