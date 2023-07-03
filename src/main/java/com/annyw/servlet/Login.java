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
        if(!uExist) {
            //Return to home with username error
            //System.out.println("user");
            request.setAttribute("uerror", "Incorrect username");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        //Check password for the username entered
        String password = request.getParameter("password");
        Client client = service.login(username,password);
        if(client == null) {
            //Return to home with password error
            //System.out.println("password");
            request.setAttribute("perror", "Incorrect password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
    
        //Store user in session
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("admin", client.getPrivillege());
        System.out.println("======");
        //Jump to Home
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
    //Register new user
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        
        /*//1.获取表单数据，并封装成user对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        //自定义转换器
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class clazz, Object value) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = null;
                try {
                    parse = format.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return parse;
            }
        }, Date.class);
        //利用工具封装到user
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //手动封装没有的数据
        user.setUid(UUID.randomUUID().toString());
        user.setTelephone(null);
        user.setState(0);
        //传递到service层
        UserService service = new UserService();
        boolean isSuccess = service.register(user);
        
        if(isSuccess) {
            //Registration success
            response.sendRedirect("QueryUserByPage");
        }else {
            ///Registration fail
            response.sendRedirect(request.getContextPath()+"/register");
        }
      */
    }
}
