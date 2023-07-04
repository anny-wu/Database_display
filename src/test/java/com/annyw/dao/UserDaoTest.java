//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.annyw.dao;

import com.annyw.pojo.User;
import com.annyw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class UserDaoTest {
    SqlSession sqlSession;
    
    public UserDaoTest() {
    }
    
    @Test
    public void test() {
        try {
            this.sqlSession = MybatisUtils.getSqlSession();
            UserDao mapper = this.sqlSession.getMapper(UserDao.class);
            
            //Setup Environment
            System.out.println("Delete table if exists:");
            mapper.checkTable("USER");
            System.out.println("Create table:");
            mapper.createTable("USER");
            System.out.println("Delete trigger if exists:");
            mapper.checkTrigger();
            System.out.println("Create trigger to record update time:");
            mapper.createTrigger("USER");
            
            //Setup Table
            System.out.println("Add user Anny:");
            mapper.addUser("USER", "Anny", 12);
            
            List<User> userList = mapper.getAllUsers("USER");
            Iterator itr = userList.iterator();
            
            while (itr.hasNext()) {
                User user = (User)itr.next();
                System.out.println(user);
            }
            System.out.println();
            
            System.out.println("Add user Tom:");
            mapper.addUser("USER", "Tom", 18);
            
            userList = mapper.getAllUsers("USER");
            itr = userList.iterator();
            
            while (itr.hasNext()) {
                User user = (User)itr.next();
                System.out.println(user);
            }
            System.out.println();
            
            System.out.println("Add user Jessica:");
            mapper.addUser("USER", "Jessica", 36);
            
            userList = mapper.getAllUsers("USER");
            itr = userList.iterator();
            
            while (itr.hasNext()) {
                User user = (User)itr.next();
                System.out.println(user);
            }
            System.out.println();
            
            System.out.println("Add user Jack:");
            mapper.addUser("USER", "Jack", 20);
            
            userList = mapper.getAllUsers("USER");
            itr = userList.iterator();
            
            while (itr.hasNext()) {
                User user = (User)itr.next();
                System.out.println(user);
            }
            System.out.println();
            
            System.out.println("Add user Catherine:");
            mapper.addUser("USER", "Catherine", 10);
            
            userList = mapper.getAllUsers("USER");
            itr = userList.iterator();
            
            while (itr.hasNext()) {
                User user = (User)itr.next();
                System.out.println(user);
            }
            System.out.println();
            
            //Perform QueryUserByPage.java
            System.out.println("Find users who are under 20:");
            userList = mapper.getUserList("USER");
            itr = userList.iterator();
            
            while (itr.hasNext()) {
                User user = (User)itr.next();
                System.out.println(user);
            }
            
            System.out.println();
            
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
}
