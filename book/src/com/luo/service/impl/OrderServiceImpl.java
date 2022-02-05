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
        //������--->Ψһ��   ��ʱ������û�Idʹ��ﵽΨһ��
        String orderId = System.currentTimeMillis() + "" + userId;
        //����һ����������
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //���涩��
        orderDao.saveOrder(order);

        //�������ﳵ�е�ÿһ����Ʒ��ת����Ϊ������浽���ݿ�
        for (Map.Entry<Integer, CartItem> entry :
                cart.getItems().entrySet()) {
            //��ȡÿһ�����ﳵ�е���Ʒ��
            CartItem cartItem = entry.getValue();
            //ת��Ϊÿһ��������
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //���涩������ݿ�
            orderItemDao.saveOrderItem(orderItem);

            //���¿�������
            Book book = bookDao.queryBookById(cartItem.getId());book.setSales(book.getSales() + cartItem.getCount());

            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }
        //��չ��ﳵ
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
