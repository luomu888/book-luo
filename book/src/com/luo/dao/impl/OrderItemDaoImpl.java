package com.luo.dao.impl;

import com.luo.dao.OrderItemDao;
import com.luo.pojo.OrderItem;

import java.util.List;


public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`totalPrice`,`orderId`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select * from t_order_item where orderId=?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
