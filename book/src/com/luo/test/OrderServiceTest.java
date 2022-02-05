package com.luo.test;

import com.luo.pojo.Cart;
import com.luo.pojo.CartItem;
import com.luo.service.OrderService;
import com.luo.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {
    private OrderService orderService=new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart=new Cart();

        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"html",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println("¶©µ¥ºÅ£º"+orderService.createOrder(cart,2));
    }
}