package com.luo.web;

import com.luo.pojo.Book;
import com.luo.pojo.Page;
import com.luo.service.BookService;
import com.luo.service.impl.BookServiceImpl;
import com.luo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService=new BookServiceImpl();

    /**
     * �����ҳ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 ��ȡ����Ĳ��� pageNo �� pageSize
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 ����BookService.page(pageNo��pageSize)��Page����
        Page<Book> page=bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //3 ����Page����Request����
        req.setAttribute("page",page);
        //4 ����ת����pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * ���ͼ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
//        1����ȡ����Ĳ���==��װ��ΪBook����
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2������BookService.addBook()����ͼ��
        bookService.addBook(book);
//        3������ͼ���б�ҳ��
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);

    }

    /**
     * ɾ��ͼ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1����ȡ����Ĳ���id��ͼ����
        int id=WebUtils.parseInt(req.getParameter("id"),0);
//        2������bookService.deleteBookById();ɾ��ͼ��
        bookService.deleteBookById(id);
//        3���ض����ͼ���б����ҳ��
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1����ȡ����Ĳ���==��װ��ΪBook����
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2������BookService.updateBook( book );�޸�ͼ��
        bookService.updateBook(book);
//        3���ض����ͼ���б����ҳ��
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * ��ȡҪ�޸�ͼ�����Ϣ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 ��ȡ����Ĳ���ͼ����
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //2 ����bookService.queryBookById��ѯͼ��
        Book book=bookService.queryBookById(id);
        //3 ���浽ͼ�鵽Request����
        req.setAttribute("book",book);
        //4 ����ת������pages/manager/book_edit.jspҳ��
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * չʾͼ���б�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 ͨ��BookService��ѯȫ��ͼ��
        List<Book> books = bookService.queryBooks();
        //2 ��ȫ��ͼ�鱣�浽Request����
        req.setAttribute("books",books);
        //3������ת����/pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
