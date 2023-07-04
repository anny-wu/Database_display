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

public class Login extends HttpServlet {
    
    //Login user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException {
        
        UserService service = new UserService();
        //Check if an username exist in the database
        String username = request.getParameter("username");
        boolean uExist = service.checkUsername(username);
        if (!uExist) {
            //Return to home with username error
            //System.out.println("user");
            request.setAttribute("uerror", "Incorrect username");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        //Check password for the username entered
        String password = request.getParameter("password");
        //Store user in session
        HttpSession session = request.getSession();
        username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
        Client client = service.login(username, password);
        session.setAttribute("username", username);
        
        if (client == null) {
            //Return to home with password error
            //System.out.println("password");
            session.setAttribute("username", null);
            request.setAttribute("perror", "Incorrect password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        session.setAttribute("admin", client.getPrivilege());
        
        //Jump to Home
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
