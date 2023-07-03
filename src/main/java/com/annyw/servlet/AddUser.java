package com.annyw.servlet;

import com.annyw.dao.UserDao;
import com.annyw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUser extends HttpServlet {
    SqlSession sqlSession;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
     try {
         this.sqlSession = MybatisUtils.getSqlSession();
         UserDao mapper = this.sqlSession.getMapper(UserDao.class);
         String table_name = request.getParameter("table_name");
         mapper.addUser(table_name, request.getParameter("username"),
             Integer.parseInt(request.getParameter("age")));
         this.sqlSession.commit();
         request.setAttribute("pageS", request.getParameter("pageS"));
         request.getRequestDispatcher("QueryUserByPage").forward(request, response);
     }
     catch (Exception e) {
         e.printStackTrace();
     }
     finally {
         if (this.sqlSession != null) {
             this.sqlSession.close();
         }
     }
     
       
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
