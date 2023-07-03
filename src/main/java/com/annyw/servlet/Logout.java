package com.annyw.servlet;

import com.annyw.pojo.Client;
import com.annyw.service.UserService;
import com.annyw.util.DBUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class Logout extends HttpServlet{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException {
        //Delete Session User
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        request.removeAttribute("username");
        
        //Reset cookie
        Cookie cookie_username = new Cookie("cookie_username","");
        Cookie cookie_password = new Cookie("cookie_password","");
        
        cookie_username.setMaxAge(0);
        cookie_password.setMaxAge(0);
        
        response.addCookie(cookie_username);
        response.addCookie(cookie_password);
        
        //Return to Home
        response.sendRedirect("index.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException {
        doGet(request, response);
    }
}
