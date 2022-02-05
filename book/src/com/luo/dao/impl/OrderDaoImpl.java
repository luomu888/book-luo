package com.luo.dao.impl;

import com.luo.dao.OrderDao;
import com.luo.pojo.Order;

import java.util.List;


public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`orderId`,`createTime`,`price`,`status`,`userId`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrder() {
        String sql = "select `orderId`,`createTime`,`price`,`status`,`userId` from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status` = ? where `orderId` = ?";
        return update(sql, status,orderId);
    }
    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select * from t_order where `userId` = ?";
        return queryForList(Order.class,sql,userId);
    }
}
