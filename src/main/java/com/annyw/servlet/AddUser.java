package com.annyw.servlet;
import com.annyw.dao.UserDaoImpl;
import com.annyw.dao.UserDao;
import com.annyw.pojo.User;
import com.annyw.util.DBUtil;
import com.annyw.util.MybatisUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;

import java.util.Iterator;
import java.util.List;

public class AddUser extends HttpServlet {
    SqlSession sqlSession;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException  {
     try {
         MybatisUtils butil = new MybatisUtils();
         this.sqlSession = butil.getSqlSession();
         UserDao mapper = this.sqlSession.getMapper(UserDao.class);
         
         String table_name = request.getParameter("table_name");
         mapper.addUser(table_name, Integer.parseInt(request.getParameter("id")), request.getParameter("username"),
             Integer.parseInt(request.getParameter("age")));
         
         List<User> userList = mapper.getAllUsers("USER");
         mapper.count("USER");
         Iterator itr = userList.iterator();
         
         while (itr.hasNext()) {
             User user = (User)itr.next();
             System.out.println(user);
         }
         mapper.count("USER");
         
         DBUtil util = new DBUtil();
         int count = util.getCount("USER");
         System.out.println(count + "db");
         
         mapper.count("USER");
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException  {
        doGet(request, response);
    }
}
