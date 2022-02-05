package com.luo.test;

import com.luo.pojo.Book;
import com.luo.pojo.Page;
import com.luo.service.BookService;
import com.luo.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"卢哥在手，天下我有！", "1125", new BigDecimal(1000000), 100000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(25);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(25,"社会我卢哥，人狠话不多！", "1125", new BigDecimal(999999), 10, 111110, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(25));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook :bookService.queryBooks()){
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}