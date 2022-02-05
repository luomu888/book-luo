package com.luo.filter;

import com.luo.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request,response);
            //�ύ����
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            //�ع�����
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//���쳣�׸�Tomcat����չʾ�ѺõĴ���ҳ��
        }
    }

    @Override
    public void destroy() {

    }
}
