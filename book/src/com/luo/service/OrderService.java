package com.luo.service;

import com.luo.pojo.Cart;
import com.luo.pojo.Order;
import com.luo.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    /**
     * ���ɶ���
     * @param cart ���ﳵ����
     * @param userId �û����
     * @return ������
     */
    String createOrder(Cart cart, Integer userId);

    /**
     * ��ѯȫ������
     * @return �����б�
     */
    List<Order> showAllOrders();

    /**
     * �������޸Ķ��� status ֵ��
     * @param orderId �������
     */
    void sendOrder(String orderId);

    /**
     * �鿴��������
     * @param orderId �������
     * @return �������б�
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * �鿴�ҵĶ���
     * @param userId �û����
     * @return �����б�
     */
    List<Order> showMyOrders(Integer userId);

    /**
     * ǩ�ն���/ȷ���ջ�
     * @param orderId �û����
     */
    void receiverOrder(String orderId);
}
