package com.annyw.servlet;

import com.annyw.dao.UserDaoImpl;
import com.annyw.pojo.Page;
import com.annyw.pojo.User;
import com.annyw.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryUserByPage extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String table_name = "TESTNEW";
        int count = DBUtil.getCount(table_name);
        
        String cPage = request.getParameter("currentPage");
        if(cPage == null)
            cPage = "1";
        int currentPage = Integer.parseInt(cPage);
        
        String cPageSize = request.getParameter("pageSize");
        if(cPageSize == null)
            cPageSize = "5";
        int pageSize = Integer.parseInt(cPageSize);
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        List<User> users = UserDaoImpl.queryUserByPage(table_name, currentPage, pageSize);
        
        Page page = new Page(pageSize, pageCount, count, currentPage, users);
        
        request.setAttribute("page", page);
        request.getRequestDispatcher("page.jsp").forward(request, response);
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
