package com.annyw.servlet;

import com.annyw.pojo.User;
import com.annyw.dao.UserDaoImpl;
import com.annyw.pojo.Page;
import com.annyw.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class EditUserByPage extends HttpServlet {
        
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
            doPost(request,response);
        }
        
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String table_name = "USER";
            int count = DBUtil.getCount(table_name);
            
            String cPage = request.getParameter("currentPage");
            if(cPage == null || cPage.equals("") ){
                cPage = "1";
            }
            int currentPage = Integer.parseInt(cPage);
            
            String pSize = request.getParameter("pageSize");
            if(pSize == null)
                pSize = "3";
            int pageSize = Integer.parseInt(pSize);
            int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            List<User> users = UserDaoImpl.queryUserByPage(table_name, currentPage, pageSize);
            
            Page<User> page = new Page<>(pageSize, count, pageCount, currentPage, users);
            
            request.setAttribute("table_name", table_name);
            request.setAttribute("page", page);
            request.setAttribute("pageS", pageSize);
            request.setAttribute("count", count);
            request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
            
            
        }
        
    }
    
