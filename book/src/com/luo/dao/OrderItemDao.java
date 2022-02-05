package com.luo.dao;

import com.luo.pojo.OrderItem;

import java.util.List;


public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem 订单项对象
     * @return 受影响的行数
     */
    int saveOrderItem(OrderItem orderItem);

    /***
     * 根据订单号查询订单
     * @param orderId 订单号
     * @return 订单列表
     */
    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
