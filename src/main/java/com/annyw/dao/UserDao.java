package com.annyw.dao;
import com.annyw.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    void checkTable(String tablename);
    void createTable(String tablename);
    void checkTrigger();
    void createTrigger(String tablename);
    void addUser(@Param("table_name") String table_name, @Param("pid") int id, @Param("puname") String username,
        @Param("page") int age);
    
    void changeName();
    List<User> getUserList(String name);
    List<User> getAllUsers(String name);
    List<User> count(String tablename);
}

