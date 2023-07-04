package com.annyw.dao;
import com.annyw.pojo.Client;
import com.annyw.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //Check if a table exists in the database
    void checkTable(String tablename);
    //Create a table in the database
    void createTable(String tablename);
    //Check if a trigger exists in the database
    void checkTrigger();
    //Create a trigger in the database
    void createTrigger(String tablename);
    //Add an user to the table USER
    void addUser(@Param("table_name") String table_name, @Param("puname") String username,
        @Param("page") int age);
    //Delete an user in the table USER
    void deleteUser(@Param("table_name") String table_name, @Param("pid") int id);
    //Update an user in the table USER
    void updateUser(@Param("table_name") String table_name, @Param("pid") int id, @Param("puname") String username,
        @Param("page") int age);
    //Add a client to the table PRIVILLEGE
    void addClient(@Param("table_name") String table_name,
        @Param("pemail") String email, @Param("puname") String username,
        @Param("spassword") String salted_password, @Param("salt") String salt,
        @Param("privilege") String privilege);
    //Perform an user query in the table USER
    List<User> getUserList(String name);
    //Get all items in a table
    List<User> getAllUsers(String name);
    //Check if there is a match in the database for the username entered
    int matchUsername(@Param("table_name") String table_name, @Param("puname") String username);
    List<Client> matchPassword(@Param("table_name") String table_name, @Param("puname") String username,
        @Param("pwd") String salted_password);
    //Get salt for the given user
    List<String> getSalt(@Param("table_name") String table_name, @Param("puname") String username);
    
    //Get all items in a table
    List<User> getAllClients(String name);
}

