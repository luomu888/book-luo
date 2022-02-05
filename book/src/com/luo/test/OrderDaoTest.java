package com.luo.test;

import com.luo.dao.OrderDao;
import com.luo.dao.impl.OrderDaoImpl;
import com.luo.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123456789",new Date(),new BigDecimal(200),0,5));
        orderDao.saveOrder(new Order("1122334455",new Date(),new BigDecimal(200),0,3));
    }
    @Test
    public void queryOrder(){
        for (Order queryorder:orderDao.queryOrder()){
            System.out.println(queryorder);
        }
    }
    @Test
    public void changeOrderStatus(){
        System.out.println(orderDao.changeOrderStatus("123456",1));
    }
    @Test
    public void queryOrderByUserId(){
        System.out.println(orderDao.queryOrderByUserId(5));
        System.out.println(orderDao.queryOrderByUserId(2));
    }
}