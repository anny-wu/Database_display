package com.annyw.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException,
        IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        String name = (String) request.getSession().getAttribute("admin");
        if (name != null && name.equals("admin")){
            //check if the user is an admin
            chain.doFilter(req, resp);
            return;
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("msg","You do not have admin privilege");
            response.sendRedirect("../index.jsp");
            return;
        }
    }
    
    public void init(FilterConfig config) throws ServletException {
    
    }
    
}
