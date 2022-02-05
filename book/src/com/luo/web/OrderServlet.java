package com.luo.web;

import com.luo.pojo.Cart;
import com.luo.pojo.Order;
import com.luo.pojo.OrderItem;
import com.luo.pojo.User;
import com.luo.service.OrderService;
import com.luo.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * ���ɶ���
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�Ȼ�ȡ Cart ���ﳵ����
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //��ȡ UserId
        User loginUser = (User) request.getSession().getAttribute("user");

        //�ж��Ƿ��½
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //�ǵü� return ,����ִ���±ߴ���
            return;
        }

        Integer userId = loginUser.getId();
        //���� orderService.createOrder(Cart,userId);���ɶ���
        String orderId = orderService.createOrder(cart, userId);

//        request.setAttribute("orderId",orderId);
        //����ת���� pages/cart/checkout.jsp
//        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);

        //��ֹ���ظ��ύ ʹ���ض��� ����֧�� request �����ݹ���

        request.getSession().setAttribute("orderId", orderId);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * �鿴�ҵĶ���
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("user");
        //�ж��Ƿ��½
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //�ǵü� return ,����ִ���±ߴ���
            return;
        }
        Integer userId = loginUser.getId();
        List<Order> myOrders = orderService.showMyOrders(userId);
        request.setAttribute("myOrders", myOrders);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    /**
     * �鿴��������
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("/pages/order/order_details.jsp").forward(request, response);
    }

    /**
     * ����
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        orderService.sendOrder(orderId);
        response.sendRedirect(request.getContextPath() + "/orderServlet?action=showAllOrders");
    }

    /**
     * �鿴���ж���
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> allOrders = orderService.showAllOrders();
        request.setAttribute("allOrders", allOrders);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * ǩ�ն���
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("user");
        //�ж��Ƿ��½
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //�ǵü� return ,����ִ���±ߴ���
            return;
        }
        String orderId = request.getParameter("orderId");
        orderService.receiverOrder(orderId);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=showMyOrders");
    }
}