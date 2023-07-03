package com.annyw.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException,
        IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        if (request.getSession().getAttribute("admin")!=null){
            //check if the user is an admin
            chain.doFilter(req, resp);
            return;
        }else {
            request.setAttribute("msg","You do not have admin privilege");  //保存错误信息到request域中，再转发
            request.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
    
    public void init(FilterConfig config) throws ServletException {
    
    }
    
}
