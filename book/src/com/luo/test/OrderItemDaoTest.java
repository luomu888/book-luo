package com.luo.test;

import com.luo.dao.OrderItemDao;
import com.luo.dao.impl.OrderItemDaoImpl;
import com.luo.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java",1,new BigDecimal(100),new BigDecimal(100),"123456"));
    }
    @Test
    public void queryOrderItemByOrderId() {
        System.out.println(orderItemDao.queryOrderItemByOrderId("123456"));
    }
}