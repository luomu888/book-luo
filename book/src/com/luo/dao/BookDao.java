package com.luo.dao;

import com.luo.pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     *添加
     * @param book 图书对象
     * @return受影响的行数
     */
    public int addBook(Book book);

    /**
     * 删除
     * @param id 图书编号
     * @return 受影响的行数
     */
    public int deleteBookById(Integer id);

    /**
     * 修改
     * @param book book对象
     * @return 受影响的行数
     */
    public int updateBook(Book book);

    /**
     * 查询单条
     * @param id 图书编号
     * @return 图书信息
     */
    public Book queryBookById(Integer id);

    /**
     * 查询多条
     * @return 多条记录
     */
    public List<Book> queryBooks();

    /**
     * 查询总页数
     * @return 总页数
     */
    Integer queryForPageTotalCount();

    /**
     * 查询当前页数据
     * @param begin 起始页数
     * @param pageSize 当前页显示的数量
     * @return 当前页数据
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 通过价钱查询总页数
     * @param min 最小价格
     * @param max 最大价格
     * @return 总页数
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 通过价钱查询当前也数据
     * @param begin 起始页数
     * @param pageSize 当前页显示的数量
     * @param min 最小价格
     * @param max 最大价格
     * @return 当前页数据
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
