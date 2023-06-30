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

public class QueryUserByPage extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBUtil util = new DBUtil();
        String table_name = "USER";
        int count = util.getCount(table_name);
        System.out.println(count);
        
        String cPage = request.getParameter("currentPage");
        if(cPage == null || cPage == "") {
            cPage = "1";
        }
        int currentPage = Integer.parseInt(cPage);
        
        String pSize = request.getParameter("pageSize");
        if(pSize == null)
            pSize = "3";
        int pageSize = Integer.parseInt(pSize);
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        List<User> users = UserDaoImpl.queryUserByPage(table_name, currentPage, pageSize);
        
        Page page = new Page(pageSize, count, pageCount, currentPage, users);
        
        
        request.setAttribute("page", page);
        request.setAttribute("table_name", table_name);
        request.setAttribute("count", count);
        request.setAttribute("pageS", pageSize);
        request.getRequestDispatcher("filtered.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("======");
    }
    
}
