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
     * 生成订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先获取 Cart 购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //获取 UserId
        User loginUser = (User) request.getSession().getAttribute("user");

        //判断是否登陆
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //记得加 return ,不让执行下边代码
            return;
        }

        Integer userId = loginUser.getId();
        //调用 orderService.createOrder(Cart,userId);生成订单
        String orderId = orderService.createOrder(cart, userId);

//        request.setAttribute("orderId",orderId);
        //请求转发到 pages/cart/checkout.jsp
//        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);

        //防止表单重复提交 使用重定向 但不支持 request 域数据共享

        request.getSession().setAttribute("orderId", orderId);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 查看我的订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("user");
        //判断是否登陆
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //记得加 return ,不让执行下边代码
            return;
        }
        Integer userId = loginUser.getId();
        List<Order> myOrders = orderService.showMyOrders(userId);
        request.setAttribute("myOrders", myOrders);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    /**
     * 查看订单详情
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
     * 发货
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
     * 查看所有订单
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
     * 签收订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("user");
        //判断是否登陆
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //记得加 return ,不让执行下边代码
            return;
        }
        String orderId = request.getParameter("orderId");
        orderService.receiverOrder(orderId);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=showMyOrders");
    }
}