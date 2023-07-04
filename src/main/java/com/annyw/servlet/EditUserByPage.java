package com.annyw.servlet;

import com.annyw.dao.UserDaoImpl;
import com.annyw.pojo.Page;
import com.annyw.pojo.User;
import com.annyw.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditUserByPage extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String table_name = "USER";
        //Get total count
        int count = DBUtil.getCount(table_name);
        //Get current page
        String cPage = request.getParameter("currentPage");
        if (cPage == null || cPage.equals("")) {
            cPage = "1";
        }
        int currentPage = Integer.parseInt(cPage);
        //Get page size
        String pSize = request.getParameter("pageS");
        if (pSize == null) {
            pSize = "3";
        }
        int pageSize = Integer.parseInt(pSize);
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        //Get users
        List<User> users = UserDaoImpl.queryUserByPage(table_name, currentPage, pageSize);
        //Create page
        Page<User> page = new Page<>(pageSize, count, pageCount, currentPage, users);
        //Set attributes for request
        request.setAttribute("pageS", pageSize);
        request.setAttribute("table_name", table_name);
        request.setAttribute("page", page);
        request.setAttribute("count", count);
        
        //Get user's privilege
        String name = (String)request.getSession().getAttribute("admin");
        //Get user's selection of privilege
        String privilege = request.getParameter("privilege");
        if (privilege != null && !privilege.equals("")) {
            //User has just selected a privilege
            request.getSession().setAttribute("privilege", privilege);
        }
        else {
            //Get user's privilege from session
            privilege = (String)request.getSession().getAttribute("privilege");
        }
        //Filter user based on privilege
        if (privilege.equals("admin")) {
            
            if (name.equals("admin")) {
                //User is admin and wants to log in as admin
                request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
            }
            else {
                //User is not admin and wants to log in as admin
                HttpSession session = request.getSession();
                session.setAttribute("msg", "You do not have admin privilege");
                response.sendRedirect("../index.jsp");
            }
        }
        else {
            //User wants to log in as user
            request.getRequestDispatcher("/user/filtered.jsp").forward(request, response);
        }
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException {
        doGet(request, response);
    }
}

    
