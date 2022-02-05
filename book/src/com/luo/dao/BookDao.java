package com.luo.dao;

import com.luo.pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     *���
     * @param book ͼ�����
     * @return��Ӱ�������
     */
    public int addBook(Book book);

    /**
     * ɾ��
     * @param id ͼ����
     * @return ��Ӱ�������
     */
    public int deleteBookById(Integer id);

    /**
     * �޸�
     * @param book book����
     * @return ��Ӱ�������
     */
    public int updateBook(Book book);

    /**
     * ��ѯ����
     * @param id ͼ����
     * @return ͼ����Ϣ
     */
    public Book queryBookById(Integer id);

    /**
     * ��ѯ����
     * @return ������¼
     */
    public List<Book> queryBooks();

    /**
     * ��ѯ��ҳ��
     * @return ��ҳ��
     */
    Integer queryForPageTotalCount();

    /**
     * ��ѯ��ǰҳ����
     * @param begin ��ʼҳ��
     * @param pageSize ��ǰҳ��ʾ������
     * @return ��ǰҳ����
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * ͨ����Ǯ��ѯ��ҳ��
     * @param min ��С�۸�
     * @param max ���۸�
     * @return ��ҳ��
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * ͨ����Ǯ��ѯ��ǰҲ����
     * @param begin ��ʼҳ��
     * @param pageSize ��ǰҳ��ʾ������
     * @param min ��С�۸�
     * @param max ���۸�
     * @return ��ǰҳ����
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
