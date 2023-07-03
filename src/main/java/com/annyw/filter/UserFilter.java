package com.annyw.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
public class UserFilter implements Filter {
    public void destroy() {
    }
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        String name = (String) request.getSession().getAttribute("admin");
        if (name != null){
            chain.doFilter(req, resp);
            return;
        }
        name = (String)request.getSession().getAttribute("username");
        if (name!=null){
            chain.doFilter(req,resp);
        }else {
            request.setAttribute("msg","Please Log In");
            request.getRequestDispatcher("index.jsp").forward(req,resp);
            System.out.println("UserFilter拦截");
        }
    }
    
    public void init(FilterConfig config) throws ServletException {
    
    }
    
}
