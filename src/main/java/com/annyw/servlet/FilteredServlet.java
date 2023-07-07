package com.annyw.servlet;

import com.annyw.dao.UserDaoImpl;
import com.annyw.pojo.Page;
import com.annyw.pojo.User;
import com.annyw.util.DBUtil;

import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
public class FilteredServlet extends HttpServlet{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try {
            //Set character encoding
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            
            String table_name = "USER";
            //Get total count
            Integer count = DBUtil.getCount(table_name);
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
            
            //Store options for the number of rows displayed per page
            Map<String, Integer> values = new HashMap<>();
            for (int i = 1; i < 5; i++) {
                values.put(String.valueOf(i), i);
            }
            
            //Get field names from User class as a list
            List<String> fieldName = new ArrayList<>();
            Class<?> cls;
            List<List<Object>> result = new ArrayList<>();
            cls = Class.forName("com.annyw.pojo.User");
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                fieldName.add(field.getName().toUpperCase());
            }
            
            //Get users from database
            for (User u : users) {
                List<Object> temp = new ArrayList<>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object obj = field.get(u);
                    
                    if (obj.getClass().getName().equals("java.sql.Timestamp")){
                        temp.add(sdf.format(obj));
                    }
                    else{
                        temp.add(obj);
                    }
                }
                result.add(temp);
            }
        
            //Set attributes for request
            //Store the pageSize selected by user
            request.setAttribute("pageS", pageSize);
            //Store the table name
            request.setAttribute("table_name", table_name);
            //Store the the number of rows in the table
            request.setAttribute("count", count);
            //Store options for the number of rows displayed per page
            request.setAttribute("values", values);
            //Store the fields provided by the USER class
            request.setAttribute("fieldName", fieldName);
            //Store the values pull from the database for each user
            request.setAttribute("result", result);
            //Store the current page
            request.setAttribute("currentPage", currentPage);
            //Store the number of pages
            request.setAttribute("totalCount", pageCount);
            
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
                request.getRequestDispatcher("/WEB-INF/template/filtered.ftl").forward(request,response);
            }
           
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
    }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            doGet(request,response);
    }
}
