package com.luo.service.impl;

import com.luo.dao.BookDao;
import com.luo.dao.impl.BookDaoImpl;
import com.luo.pojo.Book;
import com.luo.pojo.Page;
import com.luo.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<Book>();
        // ����ÿҳ��ʾ������
        page.setPageSize(pageSize);
        // ���ܼ�¼��
        Integer pageTotalCount=bookDao.queryForPageTotalCount();
        // �����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);
        // ����ҳ��
        Integer pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }
        // ������ҳ��
        page.setPageTotal(pageTotal);
        // ���õ�ǰҳ��
        page.setPageNo(pageNo);
        // ��ǰҳ���ݵĿ�ʼ����
        int begin=(page.getPageNo()-1)*pageSize;
        // ��ǰҳ����
        List<Book> items=bookDao.queryForPageItems(begin,pageSize);
        // ���õ�ǰҳ����
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<Book>();
        // ����ÿҳ��ʾ������
        page.setPageSize(pageSize);
        // ���ܼ�¼��
        Integer pageTotalCount=bookDao.queryForPageTotalCountByPrice(min,max);
        // �����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);
        // ����ҳ��
        Integer pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }
        // ������ҳ��
        page.setPageTotal(pageTotal);
        // ���õ�ǰҳ��
        page.setPageNo(pageNo);
        // ��ǰҳ���ݵĿ�ʼ����
        int begin=(page.getPageNo()-1)*pageSize;
        // ��ǰҳ����
        List<Book> items=bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // ���õ�ǰҳ����
        page.setItems(items);
        return page;
    }
}
