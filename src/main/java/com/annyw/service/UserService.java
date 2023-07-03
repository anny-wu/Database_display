package com.annyw.service;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.annyw.dao.UserDao;
import com.annyw.pojo.User;
import com.annyw.pojo.Client;
import com.annyw.dao.UserDao;
import com.annyw.util.DBUtil;
import com.annyw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class UserService {
    
    //Register new user
    public void register(Client c ) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            //TODO: Add Password check for security
            mapper.addClient("PRIVILLEGES", c.getUsername(), c.getPassword(), c.getPrivillege());
            sqlSession.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
    
    //Check if username exists in the table
    public boolean checkUsername(String username) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int count = 0;
        try {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            count = mapper.matchUsername("PRIVILEGES", username);
            return count > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return false;
    }
    
    //Log in user
    public Client login(String username, String password) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Client c = null;
        try {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            c = mapper.matchPassword("PRIVILEGES", username, password).get(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return c;
    }
    
    
}
