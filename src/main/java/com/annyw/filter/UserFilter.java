package com.annyw.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
        throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String name = (String)request.getSession().getAttribute("admin");
       
        //check if the user has valid log in
        if (name != null) {
            //check if the user is an admin
            if (name.equals("admin")) {
                chain.doFilter(req, resp);
            }
            else {
                chain.doFilter(req, resp);
            }
        }
        else {
            //Redirect the user to log in
            HttpSession session = request.getSession();
            session.setAttribute("msg", "You need to log in first.");
            response.sendRedirect("../index.jsp");
        }
    }
    
    public void init(FilterConfig config) {
    
    }
    
    public void destroy() {
    }
}
