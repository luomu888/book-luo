package com.luo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        Object user = httpServletRequest.getSession().getAttribute("user");

        if (user==null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
