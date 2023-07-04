package com.annyw.servlet;

import com.annyw.pojo.Client;
import com.annyw.util.Salt;
import com.annyw.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Register extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException {
        
        try {
            String username = request.getParameter("username");
            byte[] salt = Salt.getSalt();
            String saltstr = Base64.getEncoder().encodeToString(salt);
            System.out.println("rsalt: " + salt);
            String password = Salt.getSecurePassword(request.getParameter("password"), salt);
            System.out.println("rpassword" + password);
            String email = request.getParameter("email");
            String privilege = request.getParameter("privilege");
            Client c = new Client();
            c.setEmail(email);
            username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
            System.out.println(username);
            c.setUsername(username);
            c.setSaltedPassword(password);
            c.setSalt(saltstr);
            if (privilege.equals("admin")) {
                c.setPrivilege("admin");
            }
            else {
                c.setPrivilege("user");
            }
            System.out.println(c);
            UserService service = new UserService();
            boolean isSuccess = service.register(c);
            
            if (isSuccess) {
                //Registration success
                response.sendRedirect("index.jsp");
            }
            else {
                ///Registration fail
                request.setAttribute("unameerror", "Username already exits");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
