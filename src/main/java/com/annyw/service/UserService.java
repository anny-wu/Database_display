package com.annyw.service;
import java.io.IOException;
import java.util.Base64;

import com.annyw.pojo.Client;
import com.annyw.dao.ClientDao;
import com.annyw.util.MybatisUtils;
import com.annyw.util.Salt;
import org.apache.ibatis.session.SqlSession;

public class UserService {
    
    //Register new user
    public boolean register(Client c ) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            ClientDao mapper = sqlSession.getMapper(ClientDao.class);
            mapper.addClient("PRIVILEGES", c.getEmail(), c.getUsername(), c.getSaltedPassword(), c.getSalt(),
                c.getPrivilege());
            sqlSession.commit();
            System.out.println(mapper.getAllClients("PRIVILEGES"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (sqlSession != null) {
                sqlSession.close();
                return true;
            }
            return false;
        }
       
    }
    
    //Check if username exists in the table
    public boolean checkUsername(String username) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int count = 0;
        try {
            ClientDao mapper = sqlSession.getMapper(ClientDao.class);
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
            ClientDao mapper = sqlSession.getMapper(ClientDao.class);
            String salt = mapper.getSalt("PRIVILEGES", username).get(0);
            System.out.println("salt: " + salt);
            byte[] bsalt = Base64.getDecoder().decode(salt.getBytes("UTF-8"));
            System.out.println("byte salt: " + bsalt);
            String salted_password = Salt.getSecurePassword(password, bsalt);
            System.out.println("password: " + salted_password);
            c = mapper.matchPassword("PRIVILEGES", username, salted_password).get(0);
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
