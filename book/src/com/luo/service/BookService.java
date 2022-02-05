package com.luo.service;

import com.luo.pojo.Book;
import com.luo.pojo.Page;

import java.util.List;

public interface BookService {
    /**
     * ���
     * @param book ͼ�����
     */
    public void addBook(Book book);

    /**
     * ɾ��
     * @param id ͼ����
     */
    public void deleteBookById(Integer id);

    /**
     * �޸�
     * @param book ͼ�����
     */
    public void updateBook(Book book);

    /**
     * ��ѯ����
     * @param id ͼ����
     * @return ͼ����Ϣ
     */
    public Book queryBookById(Integer id);

    /**
     * ��ѯ����
     * @return ͼ���б�
     */
    public List<Book> queryBooks();

    /**
     * �õ���ҳ����
     * @param pageNo ��ǰҳ
     * @param pageSize ��ǰҳ��ʾ������
     * @return ͼ���ҳҳ��
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * ͨ����Ǯ��ҳ
     * @param pageNo ҳ��
     * @param pageSize ��ǰҳ��������
     * @param min ��С�۸�
     * @param max ���۸�
     * @return ͼ���ҳҳ��
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
