package com.annyw.servlet;

import com.annyw.pojo.Client;
import com.annyw.service.UserService;
import com.annyw.util.Salt;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Access extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            //Perform registration
            if (request.getParameter("action").equals("register")) {
                //Create a new user
                Client c = new Client();
                //Set email
                String email = request.getParameter("email");
                c.setEmail(email);
                //Set username
                String username = request.getParameter("username");
                //Capitalize username
                username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
                c.setUsername(username);
                //Generate salt
                byte[] salt = Salt.getSalt();
                //Encode salt bytes to string
                String saltstr = Base64.getEncoder().encodeToString(salt);
                //Generate hashed salted password
                String password = Salt.getSecurePassword(request.getParameter("password"), salt);
                //Set salted_password
                c.setSaltedPassword(password);
                //Set salt
                c.setSalt(saltstr);
                //Set privilege
                String privilege = request.getParameter("privilege");
                if (privilege.equals("admin")) {
                    c.setPrivilege("admin");
                }
                else {
                    c.setPrivilege("user");
                }
                
                //Register user
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
            else if (request.getParameter("action").equals("login")) {
                UserService service = new UserService();
                //Check if an username exists in the database
                String username = request.getParameter("username");
                boolean uExist = service.checkUsername(username);
                if (!uExist) {
                    //Return to home with username error
                    request.setAttribute("uerror", "Incorrect username");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }
                //Check password for the username entered
                String password = request.getParameter("password");
                //Store user in session
                HttpSession session = request.getSession();
                //Capitalize username
                username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
                Client client = service.login(username, password);
                //Log in fail
                if (client == null) {
                    //Return to home with password error
                    request.setAttribute("perror", "Incorrect password");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }
                session.setAttribute("username", username);
                session.setAttribute("admin", client.getPrivilege());
                
                //Jump to home for filter
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else if (request.getParameter("action").equals("logout")) {
                //Delete Session User
                HttpSession session = request.getSession();
                session.removeAttribute("username");
                session.removeAttribute("privilege");
                session.removeAttribute("msg");
                request.removeAttribute("username");
                
                //Reset cookie
                Cookie cookie_username = new Cookie("cookie_username", "");
                Cookie cookie_password = new Cookie("cookie_password", "");
                
                cookie_username.setMaxAge(0);
                cookie_password.setMaxAge(0);
                
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
                
                //Return to Home
                response.sendRedirect("index.jsp");
            }
            
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
