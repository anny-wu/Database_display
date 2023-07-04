package com.annyw.dao;

import com.annyw.pojo.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientDao {
    //Add a client to the table PRIVILLEGE
    void addClient(@Param("table_name") String table_name,
        @Param("pemail") String email, @Param("puname") String username,
        @Param("spassword") String salted_password, @Param("salt") String salt,
        @Param("privilege") String privilege);
    
    //Check if there is a match in the database for the username entered
    int matchUsername(@Param("table_name") String table_name, @Param("puname") String username);
    
    //Check if the stored password matches user entered password
    List<Client> matchPassword(@Param("table_name") String table_name, @Param("puname") String username,
        @Param("pwd") String salted_password);
    
    //Get salt for the given user
    List<String> getSalt(@Param("table_name") String table_name, @Param("puname") String username);
    
    //Get all items in a table
    List<Client> getAllClients(String name);
}
