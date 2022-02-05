package com.luo.service.impl;

import com.luo.dao.BookDao;
import com.luo.dao.OrderDao;
import com.luo.dao.OrderItemDao;
import com.luo.dao.impl.BookDaoImpl;
import com.luo.dao.impl.OrderDaoImpl;
import com.luo.dao.impl.OrderItemDaoImpl;
import com.luo.pojo.*;
import com.luo.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author LMQ
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号--->唯一性   用时间戳加用户Id使其达到唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        //遍历购物车中的每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry :
                cart.getItems().entrySet()) {
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());book.setSales(book.getSales() + cartItem.getCount());

            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrder();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public void receiverOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
