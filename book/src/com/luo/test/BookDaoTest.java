package com.luo.test;

import com.luo.dao.BookDao;
import com.luo.dao.impl.BookDaoImpl;
import com.luo.pojo.Book;
import com.luo.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"卢哥为什么这么帅","2002",new BigDecimal(9999),1000000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"大家都可以这么帅！", "卢哥", new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBook() {
        for (Book queryBook:bookDao.queryBooks()){
            System.out.println(queryBook);
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void  queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }
    @Test
    public void  queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50)) {
            System.out.println(book);
        }
    }
}