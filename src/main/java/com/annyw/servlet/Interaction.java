package com.annyw.servlet;

import com.annyw.dao.UserDao;
import com.annyw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Interaction extends HttpServlet {
    SqlSession sqlSession;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            //Establish sqlSession
            this.sqlSession = MybatisUtils.getSqlSession();
            UserDao mapper = this.sqlSession.getMapper(UserDao.class);
            //Get table name
            String table_name = request.getParameter("table_name");
            //Add user
            if (request.getParameter("action").equals("add")) {
                mapper.addUser(table_name, request.getParameter("username"),
                    Integer.parseInt(request.getParameter("age")));
            }
            //Delete user
            else if (request.getParameter("action").equals("delete")) {
                mapper.deleteUser(table_name, Integer.parseInt(request.getParameter("deleted")));
            }
            //Edit user
            else if(request.getParameter("action").equals("edit")){
                mapper.updateUser(table_name, Integer.parseInt(request.getParameter("selected")), request.getParameter(
                    "username"), Integer.parseInt(request.getParameter("age")));
                
            }
            this.sqlSession.commit();
            //Redirect back to database display
            request.setAttribute("pageS", request.getParameter("pageS"));
            request.getRequestDispatcher("/user/EditUserByPage").forward(request, response);
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
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }
}
