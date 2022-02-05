package com.luo.web;

import com.google.gson.Gson;
import com.luo.pojo.Book;
import com.luo.pojo.Cart;
import com.luo.pojo.CartItem;
import com.luo.service.BookService;
import com.luo.service.impl.BookServiceImpl;
import com.luo.utils.WebUtils;
import com.sun.org.apache.xpath.internal.objects.XObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();


    /**
     * ɾ����Ʒ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);

        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * ���빺�ﳵ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ��ȡ����Ĳ��� ��Ʒ���
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        // ����bookService.queryBookById(id):Book�õ�ͼ�����Ϣ
        Book book=bookService.queryBookById(id);
        // ��ͼ����Ϣ��ת����ΪCartItem��Ʒ��
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // ����Cart.addItem(CartItem);�����Ʒ��
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        // ���һ����ӵ���Ʒ����
        req.getSession().setAttribute("lastName",cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ��ȡ����Ĳ��� ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // ����bookService.queryBookById(id):Book�õ�ͼ�����Ϣ
        Book book = bookService.queryBookById(id);
        // ��ͼ����Ϣ��ת����ΪCartItem��Ʒ��
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // ����Cart.addItem(CartItem);�����Ʒ��
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        // ���һ����ӵ���Ʒ����
        req.getSession().setAttribute("lastName", cartItem.getName());

        //6�����ع��ﳵ�ܵ���Ʒ���������һ����ӵ���Ʒ����
        Map<String,Object> resultMap=new HashMap<String,Object>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson=new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);

    }

    /**
     * ��չ��ﳵ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * �޸���Ʒ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ����Ĳ�������Ʒ��ţ���Ʒ����
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //��ȡ Cart ���ﳵ����
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //�޸���Ʒ����
            cart.updateCount(id,count);
            // �ض����ԭ���Ĺ��ﳵչʾҳ��
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
