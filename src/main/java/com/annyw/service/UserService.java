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
        boolean exist = false;
        try {
            //Attempt to add new user to the database
            if(checkUsername(c.getUsername())){
                System.out.println("User exists");
                exist = true;
            }
            else {
                System.out.println("happen");
                ClientDao mapper = sqlSession.getMapper(ClientDao.class);
                mapper.addClient("PRIVILEGES", c.getEmail(), c.getUsername(), c.getSaltedPassword(), c.getSalt(),
                    c.getPrivilege());
                sqlSession.commit();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (sqlSession != null && !exist){
                sqlSession.close();
                return true;
            }
            return false;
        }
       
    }
    
    //Check if username exists in the table
    public boolean checkUsername(String username) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            ClientDao mapper = sqlSession.getMapper(ClientDao.class);
            int count = mapper.matchUsername("PRIVILEGES", username);
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
            //Get salt string stored for a username
            ClientDao mapper = sqlSession.getMapper(ClientDao.class);
            String salt = mapper.getSalt("PRIVILEGES", username).get(0);
            //Convert salt to its original byte format
            byte[] bsalt = Base64.getDecoder().decode(salt.getBytes("UTF-8"));
            //Get salted password
            String salted_password = Salt.getSecurePassword(password, bsalt);
            //Check if the salted password matches the one stored in the database
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
