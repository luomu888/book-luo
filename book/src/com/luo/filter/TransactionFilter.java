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
            //提交事务
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            //回滚事务
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat管理展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
